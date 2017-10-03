package hotciv.standard;

import hotciv.variations.deltaciv.DeltaFactory;
import hotciv.framework.*;
import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by jrt on 16/11/15.
 */
public class TestDeltaCiv {

    private GameImpl game;

    @Before
    public void setUp() {

        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void shouldHaveRedCityAt8_12(){
        City city = game.getCityAt(new Position(8,12));
        assertThat("should be a red city at (8,12)", city.getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAt4_5(){
        City city = game.getCityAt(new Position(4,5));
        assertThat("should be a blue city at (4,5)", city.getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveOceanAt0_0(){
        Tile tile = game.getTileAt(new Position(0,0));
        assertThat("should be a ocean city at (0,0)", tile.getTypeString(), is(GameConstants.OCEANS));
    }
}
