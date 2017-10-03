package hotciv.standard;

import hotciv.variations.betaciv.BetaFactory;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by smp on 09/11/15.
 */
public class TestBetaCiv {
    private GameImpl game;

    @Before
    public void setUp() {

        game = new GameImpl(new BetaFactory());
    }

    @Test
    public void shouldAge100YearsBetween4000bcAnd100bc(){
        int oldAge = game.getAge();
        game.endRoundNumberOfTimes(1);
        int newAge = game.getAge();
        assertThat("Age should have advanced with 100 ", newAge - oldAge, is(100));
    }

    @Test
    public void shouldAgeInSequenceAroundBC(){

        game.endRoundNumberOfTimes(39);
        assertThat("Age should be 100 BC", game.getAge(), is(-100));

        game.endRoundNumberOfTimes(1);
        assertThat("Age should be 1 BC ", game.getAge(), is(-1));

        game.endRoundNumberOfTimes(1);
        assertThat("Age should be 1 AD ", game.getAge(), is(1));

        game.endRoundNumberOfTimes(1);
        assertThat("Age should be 50 ", game.getAge(), is(50));

        game.endRoundNumberOfTimes(34);
        assertThat("Age should be 1750 ", game.getAge(), is(1750));

        game.endRoundNumberOfTimes(6);
        assertThat("Age should be 1900 ", game.getAge(), is(1900));

        game.endRoundNumberOfTimes(14);
        assertThat("Age should be 1970 ", game.getAge(), is(1970));

        game.endRoundNumberOfTimes(30);
        assertThat("Age should be 2000 ", game.getAge(), is(2000));

    }

    @Test
    public void winnerShouldBePlayerThatFirstConquersAllCities(){

        //It is blue's turn
        game.endOfTurn();
        Position startPosition = new Position(3,2); // BLUE Unit at this position
        Position redPosition = new Position(1,1); // RED City at this position

        boolean moveIsSuccesful = game.moveUnit(startPosition, redPosition);

        assertTrue("Move is succesful", moveIsSuccesful);
        assertThat("Winner is blue", game.getWinner(), is(Player.BLUE));
    }
}

