package hotciv.framework.interfaces.ResourceStrategy.implementations;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Resource.implementations.Food;
import hotciv.framework.interfaces.Resource.implementations.Production;
import hotciv.framework.interfaces.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TestFactory;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by smp on 30/11/15.
 */
public class TestResourceGathering {

    private GameImpl game;

    @Before
    public void setUp() {

        game = new GameImpl(new TestFactory());
    }

    @Test
    public void shouldGatherFoodFromNeighborhoodTiles(){

        AdvancedResourceStrategy strategy = new AdvancedResourceStrategy();

        // city surrounded by 2 plains, 2 mountains, 2 forest, 2 oceans, start size = 1

        Position cityPos = new Position(8,8);
        CityImpl city = (CityImpl) game.getCityAt(cityPos);
        game.changeWorkForceFocusInCityAt(cityPos, Food.class.getName()); // focus on food: priority --> {GameConstants.PLAINS  (3) , GameConstants.OCEANS (1) }

        strategy.gather(city, game, 0);
        assertThat("Gathering food with 0 workers should give 1 food", city.getFoodCount(), is(1)); // 1 from city

        city.setFoodCount(0);

        strategy.gather(city, game, 1);
        assertThat("Gathering food with 1 workers should give 4 food", city.getFoodCount(), is(4)); // 1 from city + 3 from plains

        city.setFoodCount(0);

        strategy.gather(city, game, 2);
        assertThat("Gathering food with 2 workers should give 7 food", city.getFoodCount(), is(7)); // 1 from city + 6 from plains

        city.setFoodCount(0);

        strategy.gather(city, game, 3);
        assertThat("Gathering food with 3 workers should give 0 food, but evolve city", city.getFoodCount(), is(0)); // 1 from city + 6 from plains + 1 from Ocean
        assertThat("City Size should be 2", city.getSize(), is(2)); // 2 because we started with 1 but gather with different values

        strategy.gather(city, game, 4);
        assertThat("Gathering food with 4 workers should give 9 food and not evolve, new upgrade requirement is 5 + 2 * 3 = 11", city.getFoodCount(), is(9)); // 1 from city + 6 from plains + 2 from Oceans
        assertThat("City Size should be 2 and not evolved further", city.getSize(), is(2));
    }
    @Test
    public void shouldGatherProductionFromNeighborhoodTiles(){

        AdvancedResourceStrategy strategy = new AdvancedResourceStrategy();

        // city surrounded by 2 plains, 2 mountains, 2 forest, 2 oceans, start size = 1

        Position cityPos = new Position(8,8);
        CityImpl city = (CityImpl) game.getCityAt(cityPos);
        game.changeWorkForceFocusInCityAt(cityPos, Production.class.getName()); // focus on food: priority --> {GameConstants.FOREST (3), GameConstants.HILLS (2), GameConstants.MOUNTAINS (1)};

        strategy.gather(city, game, 0);
        assertThat("Gathering production with 0 workers should give 1 production", city.getProductionCount(), is(1)); // 1 from city


        strategy.gather(city, game, 1);
        assertThat("Gathering production with 1 workers should give 4 production", city.getProductionCount(), is(5)); // 1 from city + 3 from forest


        strategy.gather(city, game, 2);
        assertThat("Gathering production with 2 workers should give 7 production", city.getProductionCount(), is(12)); // 1 from city + 6 from forest


        strategy.gather(city, game, 3);
        assertThat("Gathering production with 3 workers", city.getProductionCount(), is(20)); // 1 from city + 6 from forest + 1 from mountains

        strategy.gather(city, game, 4);
        assertThat("Production keeps on adding up, now at 29", city.getProductionCount(), is(29)); // 1 from city + 6 from forest + 2 from mountains
    }
    @Test
    public void shouldAllowCitiesToProduceUnitsIfEnoughProductionIsAvailable(){
        Position cityPos = new Position(8,8);
        CityImpl city = (CityImpl) game.getCityAt(cityPos);
        game.endRoundNumberOfTimes(50);
        city.setProduction(GameConstants.ARCHER);
        Unit unitProduced = city.produceUnit(game);
        assertNotNull("The city produces an Archer, cuz...", unitProduced);
    }


}
