package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Resource.implementations.Food;
import hotciv.framework.interfaces.Unit;

/**
 * Created by jrt on 02/11/15.
 */
public class CityImpl implements City {
    private final Position position;
    Player owner;
    private String production = GameConstants.ARCHER;
    private int productionCount;
    private String workFocus = Food.class.getName();
    private int foodCount;
    private int size = 1;

    public CityImpl(Player owner, Position position) {
        this.owner = owner;
        this.position = position;
    }
    /**
     * return the owner of this city.
     *
     * @return the player that controls this city.
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * return the size of the population.
     *
     * @return population size.
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * return the type of unit this city is currently producing.
     *
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    @Override
    public String getUnitInProduction() {
        return production;
    }


    /**
     * return the work force's focus in this city.
     *
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    @Override
    public String getWorkforceFocus() {
        return workFocus;
    }

    /**
     * return the unit produced by the city.
     *
     * @return a unit produced
     * @param game
     */
    @Override
    public Unit produceUnit(Game game) {
        int unitCost = game.getUnitConstants().getUnitCosts().get(getUnitInProduction());
        if(productionCount >= unitCost) {
            productionCount -=unitCost;
            return new UnitImpl(getOwner(), getUnitInProduction(), game.getUnitStateStrategy(), game);
        } else{
            return null;
        }
    }

    @Override
    public int getProductionCount() {
        return productionCount;
    }

    @Override
    public int getFoodCount() {
        return foodCount;
    }


    public String getProduction() {
        return production;
    }

    /***
     * Sets the type of unit this city is currently producing.
     * @param production
     */
    public void setProduction(String production) {
        this.production = production;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void changeWorkFocus(String balance) {
        this.workFocus = balance;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;

        // city size is limited to 9
        if(size == 9){
            return;
        }

        int upgradeCost = 5 + getSize() * 3;
        if(foodCount >= upgradeCost){
            size++;
            this.foodCount = 0;
        }
    }

    public void setProductionCount(int productionCount) {
        this.productionCount = productionCount;
    }

    public Position getPosition() {
        return position;
    }
}
