package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Unit;
import hotciv.framework.interfaces.UnitState.UnitState;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;

/**
 * Created by smp on 02/11/15.
 */
public class UnitImpl implements Unit{

    private Game game;
    private UnitStateStrategy unitStateStrategy;
    Player owner;
    String typeString;
    UnitState state, defaultState, alternateState;

    public UnitImpl(Player owner, String typeString, UnitStateStrategy unitStateStrategy, Game game) {
        this.game = game;
        this.owner = owner;
        this.typeString = typeString;

        this.defaultState = unitStateStrategy.getDefaultState(typeString, game);
        this.alternateState = unitStateStrategy.getAlternateState(typeString, game);

        state = defaultState;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return state.getMoveCount(game);
    }

    @Override
    public int getDefensiveStrength() {
        return state.getDefensiveStrength(game);
    }

    @Override
    public int getAttackingStrength() {
        return state.getAttackingStrength(game);
    }

    public void changeState(){
        state = state.changeState(game);
    }

}
