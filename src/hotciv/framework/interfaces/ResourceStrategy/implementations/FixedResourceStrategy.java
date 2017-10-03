package hotciv.framework.interfaces.ResourceStrategy.implementations;

import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 30/11/15.
 */
public class FixedResourceStrategy implements ResourceStrategy {
    @Override
    public void gatherResources(GameImpl game) {
        for(City c:game.getCities().values()){
            CityImpl city = (CityImpl) c;
            gather(city);
        }
    }

    private void gather(CityImpl city){
        //city.setFoodCount(city.getFoodCount());
        city.setProductionCount(city.getProductionCount() + 6);
    }
}
