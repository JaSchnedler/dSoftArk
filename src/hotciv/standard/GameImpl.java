package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.interfaces.GameObserver.GameObserver;
import hotciv.framework.interfaces.UnitConstantsStrategy.UnitConstantsStrategy;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;
import hotciv.framework.interfaces.AttackCountStrategy.AttackCountStrategy;
import hotciv.framework.interfaces.*;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.framework.interfaces.GameState.implementations.GameStateImpl;
import hotciv.framework.interfaces.Resource.Resource;
import hotciv.framework.interfaces.Resource.implementations.Food;
import hotciv.framework.interfaces.Resource.implementations.Mixed;
import hotciv.framework.interfaces.Resource.implementations.Production;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;
import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {

    private final GameFactory factory;

    private StateChangeStrategy stateChangeStrategy;
    private WorldStrategy worldStrategy;

    // GAME STATE FIELDS
    Player playerInTurn = Player.RED;
    private int gameAge = -4000;

    // STRATEGIES
    public AgeStrategy ageStrategy;

    // WORLD
    private HashMap<Position,City> cities = new HashMap<>();
    private HashMap<Position,Unit> units = new HashMap<>();
    private HashMap<Position,Tile> tiles = new HashMap<>();
    private ArrayList<Player> players = new ArrayList<>();

    private UnitActionStrategy unitActionStrategy;
    private CombatStrategy combatStrategy;
    private int roundsPassed;
    private GameState state, defaultState,alternateState;
    private AttackCountStrategy attackCountStrategy;
    private ResourceStrategy resourceStrategy;
    private HashMap<String, Resource> tileData = new HashMap<>();
    private HashMap<String,Integer> unitCosts = new HashMap<>();
    private UnitStateStrategy unitStateStrategy;
    private UnitConstantsStrategy unitConstants;
    private List<GameObserver> observers = new ArrayList<>();

    public GameImpl(GameFactory fac) {
        this.factory = fac;

        this.worldStrategy = factory.createWorldStrategy();
        this.ageStrategy = factory.createAgeStrategy();
        this.unitActionStrategy = factory.createUnitActionStrategy();
        this.combatStrategy = factory.createCombatStrategy();
        this.defaultState = factory.createDefaultGameState();
        this.alternateState = factory.createAlternateGameState();
        this.stateChangeStrategy = factory.createStateChangeStrategy();
        this.resourceStrategy = factory.createResourceStrategy();
        this.unitStateStrategy = factory.createUnitStateStrategy();
        this.unitConstants = factory.createUnitConstantsStrategy();

        //Initialization
        this.stateChangeStrategy.setGame(this);
        this.state = defaultState;
        this.worldStrategy.createWorld(this);
        tileData.put(GameConstants.OCEANS, new Food(1, 0, false));
        tileData.put(GameConstants.PLAINS, new Food(3, 0, true));
        tileData.put(GameConstants.FOREST, new Production(0, 3, true));
        tileData.put(GameConstants.MOUNTAINS, new Production(0, 1, false));
        tileData.put(GameConstants.HILLS, new Production(0, 2, true));
        tileData.put(City.class.getName(), new Mixed(1, 1, true));

    }

    public Tile getTileAt(Position p) {
        return tiles.get(p);
    }

    public Unit getUnitAt(Position p) {
        return units.get(p);
    }

    public City getCityAt(Position p) {
        return cities.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return state.getWinner(this);
    }

    public int getAge() {
        return gameAge;
    }

    public boolean moveUnit(Position from, Position to) {

        Unit unit = getUnitAt(from);
        // FAIL FAST
        if(!unit.getOwner().equals(getPlayerInTurn())){
            notifyObserversMoveUnit(to, from, false);

            return false;
        }

        // RETURN FAST IF THE TILE IS FREE
        if (tileIsFree(to)) {
            notifyObserversMoveUnit(to, from, true);
            units.remove(unit);
            units.put(to, unit);
            return true;
        }

        // CONQUER CITY IF POSSIBLE
        CityImpl city = (CityImpl) getCityAt(to);
        if(city != null){
            city.setOwner(unit.getOwner());
            notifyObserversMoveUnit(to, from, true);

            return true;
        }

        // IF NOT, CHECK IF THE UNIT IS HOSTILE TO ENGAGE COMBAT
        Unit target = getUnitAt(to);

        if(enemeyUnit(target)){
            // engage in battle
            boolean battleWon = combatStrategy.startCombat(this, from, to);
            state.incrementAttackCounter(this);
            notifyObserversMoveUnit(to, from, battleWon);

            units.remove(unit);
            units.put(to, unit);

            return battleWon;
        }
        notifyObserversMoveUnit(to, from, false);

        // ELSE DENY MOVE
        return false;
    }

    private boolean tileIsFree(Position to) {
        return getUnitAt(to) == null && getCityAt(to) == null;
    }

    private boolean enemeyUnit(Unit target) {
        if(target != null && target.getOwner() != getPlayerInTurn()){
            return true;
        }
        return false;
    }

    public void endOfTurn() {
        if(playerInTurn == Player.BLUE){
            gameAge = ageStrategy.calculateNewAge(gameAge);
            playerInTurn = Player.RED;
            roundsPassed++;

            resourceStrategy.gatherResources(this);

            state = stateChangeStrategy.changeStateIfPossible();
            notifyObserversRoundEnded();
        }else {

            playerInTurn = Player.BLUE;
        }
    }


    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        CityImpl city = (CityImpl) cities.get(p);
        city.changeWorkFocus(balance);

    }

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl city = (CityImpl) getCityAt(p);
        city.setProduction(unitType);
    }

    public void performUnitActionAt(Position p) {
        unitActionStrategy.performUnitAction(this, p);
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public HashMap<Position, City> getCities() {
        return cities;
    }

    public HashMap<Position, Unit> getUnits() {
        return units;
    }

    public HashMap<Position, Tile> getTiles() { return tiles; }

    @Override
    public UnitStateStrategy getUnitStateStrategy() {
        return unitStateStrategy;
    }

    @Override
    public UnitConstantsStrategy getUnitConstants() {
        return unitConstants;
    }

    public HashMap<String, Resource> getTileData(){ return tileData; }

    @Override
    public void endRoundNumberOfTimes(int times) {
        for(int i = 0; i < times; i++) {
            if (playerInTurn.equals(Player.RED)) {
                endOfTurn();
                endOfTurn();
            } else {
                endOfTurn();
            }
        }
    }

    public Integer getRoundsPassed() {
        return roundsPassed;
    }

    public GameState getState() {
        return state;
    }

    public GameState getDefaultState() {
        return defaultState;
    }

    public GameState getAlternateState() {
        return alternateState;
    }

    public AttackCountStrategy getAttackCountStrategy() {
        return ((GameStateImpl)state).getAttackCountStrategy();
    }

    public void addObserver(GameObserver gameObserver) {
        if(!observers.contains(gameObserver)) {
            observers.add(gameObserver);
        }
    }

    public void removeObserver(GameObserver gameObserver) {
        if(observers.contains(gameObserver)) {
            observers.remove(gameObserver);
        }
    }
    public List<GameObserver> getObservers() {
        return observers;
    }


    public void notifyObserversRoundEnded() {
        for(GameObserver observer : observers){
            observer.onRoundEnded(getRoundsPassed());
        }
    }

    public void notifyObserversMoveUnit(Position to, Position from, Boolean validity) {
        for(GameObserver observer : observers){
            observer.onUnitMoved(getUnitAt(from), from, to, validity);
        }
    }

    public void notifyDiceRolled(int d1, int d2) {
        for(GameObserver observer : observers){
            observer.onDiceRolled(d1,d2);
        }
    }
}


