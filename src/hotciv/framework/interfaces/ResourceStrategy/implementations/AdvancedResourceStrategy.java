package hotciv.framework.interfaces.ResourceStrategy.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;
import hotciv.framework.interfaces.Tile;
import hotciv.framework.interfaces.WorldStrategy.WorldUtility;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.framework.interfaces.Resource.implementations.Production;

import java.util.Iterator;

/**
 * Created by smp on 30/11/15.
 */
public class AdvancedResourceStrategy implements ResourceStrategy {
    @Override
    public void gatherResources(GameImpl game) {
        for(City c:game.getCities().values()){
            CityImpl city = (CityImpl) c;
            int workersAvailable = city.getSize() - 1; // the city is mandatory
            gather(city, game, workersAvailable);
        }
    }

    protected void gather(CityImpl city, GameImpl game, int workersAvailable){

        // always gather production and food from the city (Mixed)
        int newFoodCount = city.getFoodCount() + game.getTileData().get(City.class.getName()).getFoodYield();
        int newProductionCount = city.getProductionCount() + game.getTileData().get(City.class.getName()).getProductionYield();

        // gather resources based on work focus
        if(city.getWorkforceFocus().equals(Production.class.getName())){
            newProductionCount += gatherProduction(city, game, workersAvailable);

            // gather food if we have available workers
            if(workersAvailable > 0){
                gatherFood(city, game, workersAvailable);
            }
        } else {
            newFoodCount += gatherFood(city, game, workersAvailable);

            // if there isn't enough food available, gather production instead
            if(workersAvailable > 0){
                gatherProduction(city, game, workersAvailable);
            }

        }

        city.setFoodCount(newFoodCount);
        city.setProductionCount(newProductionCount);
    }

    protected int gatherFood(CityImpl city, GameImpl game, int workersAvailable) {

        String[] priorities = new String[]{GameConstants.PLAINS, GameConstants.OCEANS};
        int newFood = 0;

        for(String s : priorities){
            Iterator<Position> iterator = WorldUtility.get8NeighborhoodIterator(city.getPosition());

            while (iterator.hasNext() && workersAvailable > 0){
                Tile tile = game.getTileAt(iterator.next());

                if(tile.getTypeString().equals(s)){
                    newFood += game.getTileData().get(s).getFoodYield();
                    workersAvailable--;
                }
            }
        }

        return newFood;
    }

    protected int gatherProduction(CityImpl city, GameImpl game, int workersAvailable){

        String[] priorities = new String[]{GameConstants.FOREST, GameConstants.HILLS, GameConstants.MOUNTAINS};
        int newProduction = 0;

        for(String s : priorities){
            Iterator<Position> iterator = WorldUtility.get8NeighborhoodIterator(city.getPosition());

            while (iterator.hasNext() && workersAvailable > 0){
                Tile tile = game.getTileAt(iterator.next());

                if(tile.getTypeString().equals(s)){
                    newProduction += game.getTileData().get(s).getProductionYield();
                    workersAvailable--;
                }
            }
        }


        return newProduction;
    }
}
