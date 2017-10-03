package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.interfaces.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Unit;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.DefaultUnitStateStrategy;
import hotciv.variations.zetaciv.ZetaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jrt on 26/11/15.
 */
public class TestZetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory());
    }

    @Test
    public void shouldHaveWinnerWhoConquerersAllCitiesBefore20Rounds(){
        //Below 19 rounds
        game.endRoundNumberOfTimes(19);

        //First city is Blue

        Position bluePosition = new Position(4,4);
        City blueCity = game.getCityAt(bluePosition);

        //Second city is Red

        Position redPosition = new Position(1,1);
        City redCity = game.getCityAt(redPosition);

        //It is blue's turn
        game.endOfTurn();
        Position startPosition = new Position(3,2);
        Unit blueUnit = game.getUnitAt(startPosition);
        boolean moveIsSuccesful = game.moveUnit(startPosition, redPosition);


        assertTrue("Move is succesful", moveIsSuccesful);
        assertThat("Winner is blue", game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveWinnerToBeWinnerOfThreeAttacksAfterRound20(){

        game.endRoundNumberOfTimes(20);
        //Did 20 rounds pass?
        assertThat("20 rounds has passed", game.getRoundsPassed(), is(20));

        //3 attacks

        boolean validAttack1 = game.moveUnit(new Position(2, 0), new Position(3,2));
        assertTrue("Attack 1 is successful", validAttack1);

        resetUnitsInGame(game);

        boolean validAttack2 = game.moveUnit(new Position(2, 0), new Position(3, 2));
        assertTrue("Attack 2 is successful", validAttack2);

        resetUnitsInGame(game);

        boolean validAttack3 = game.moveUnit(new Position(2, 0), new Position(3, 2));
        assertTrue("Attack 3 is successful", validAttack3);



        //Did the winner of the battles win the game?
        assertThat("Winner should be Red", game.getWinner(), is(Player.RED));
    }


    private void resetUnitsInGame(Game game){
        game.getUnits().clear();

        UnitStateStrategy unitStateStrategy = new DefaultUnitStateStrategy();

        game.getUnits().put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER, unitStateStrategy, game));
        game.getUnits().put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION, unitStateStrategy, game));
        game.getUnits().put(new Position(0, 0), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));

        game.getTiles().put(new Position(0, 0), new TileImpl(GameConstants.PLAINS));
        game.getTiles().put(new Position(0, 1), new TileImpl(GameConstants.MOUNTAINS));
        game.getTiles().put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));

    }
}


