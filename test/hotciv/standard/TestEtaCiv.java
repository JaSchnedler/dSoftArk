package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Resource.implementations.Food;
import hotciv.framework.interfaces.Resource.implementations.Production;
import hotciv.variations.etaciv.EtaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jacob on 30-11-2015.
 */
public class TestEtaCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {

        game = new GameImpl(new EtaFactory());
    }
    @Test
    public void productionFocusShouldBeSettable(){
        Position cityPos = new Position(8,8);
        City city = game.getCityAt(cityPos);
        String initialFocus = city.getWorkforceFocus();
        assertThat("Production focus should be food", initialFocus,is(Food.class.getName()));
        game.changeWorkForceFocusInCityAt(cityPos,Production.class.getName());
        assertThat("Production focus should be production", city.getWorkforceFocus(),is(Production.class.getName()));
        game.changeWorkForceFocusInCityAt(cityPos,Food.class.getName());
        assertThat("Production focus should be food", city.getWorkforceFocus(),is(Food.class.getName()));
    }

    @Test
    public void citySizeIncreasesWhenCollectingFood(){
        Position cityPos = new Position(8,8);
        City city = game.getCityAt(cityPos);
        int initial = city.getSize();
        assertThat("Production focus should be food", city.getWorkforceFocus(),is(Food.class.getName()));
        game.endRoundNumberOfTimes(50);
        int notInitial = city.getSize();
        boolean difference = initial < notInitial;
        assertTrue("The city grows",difference);

        // using the fixed world layout, we know that all adjacent tiles are plains


    }
    @Test
    public void shouldNotAllowCitiesToGrowAboveLevel9(){
        Position cityPos = new Position(8,8);
        City city = game.getCityAt(cityPos);
        game.endRoundNumberOfTimes(50);
        boolean citySize = city.getSize() < 10;
        assertTrue("The city does not grow above size 9",citySize);
    }
    @Test
    public void shouldResetFoodAmountUponCityIncrease(){
        Position cityPos = new Position(8,8);
        City city = game.getCityAt(cityPos);
        game.endRoundNumberOfTimes(1);
        boolean initial = city.getFoodCount() > 0;
        assertTrue("The city has resources",initial);
        //End turn the amount of times it takes for the city to create food enough
        game.endRoundNumberOfTimes(7);
        int afterProduction = city.getFoodCount();
        assertThat("The city has resources",afterProduction, is(0));

    }


}
