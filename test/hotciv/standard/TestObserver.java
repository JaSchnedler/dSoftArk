package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.CombatStrategy.implementations.AdvancedCombatStrategy;
import hotciv.framework.interfaces.DiceStrategy.implementations.FixedDiceStrategy;
import hotciv.framework.interfaces.GameObserver.implementations.DefaultGameObserver;
import hotciv.framework.interfaces.GameObserver.GameObserver;
import hotciv.framework.interfaces.Unit;
import hotciv.variations.alphaciv.AlphaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jrt on 26/11/15.
 */
public class TestObserver {

    private GameImpl game;
    private GameObserver gameObserver;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
    }

    @Test
    public void shouldBeAbleToAddAndRemoveObserver(){
        game.addObserver(gameObserver);
        assertNotNull("An observer is on game",game.getObservers());

        boolean notEmpty = !game.getObservers().isEmpty();
        assertTrue("list length is > 0", notEmpty);

        game.removeObserver(gameObserver);
        boolean empty = game.getObservers().isEmpty();
        assertTrue("Observerlist is empty", empty);
    }
    @Test
    public void observersCanPrintRoundsMovesAndRolls(){
        game.addObserver(new DefaultGameObserver());
        game.moveUnit(new Position(2, 0), new Position(2, 1)); //see terminal

        game.endRoundNumberOfTimes(2);

    }
    @Test
    public void shouldGiveValidCallbacks(){

        game = new GameImpl(new TestFactory());

        final Position oldFrom = new Position(1,2);
        final Position oldTo = new Position(1,1);

        game.addObserver(new GameObserver() {
            final int staticRound = 0;

            @Override
            public void onRoundEnded(Integer roundsPassed) {
                boolean difference = roundsPassed != staticRound;
                assertTrue("There is a difference", difference);
            }

            @Override
            public void onUnitMoved(Unit unitAt, Position from, Position to, Boolean validity) {

                assertTrue("Should be a valid move", validity);
                assertTrue("from and to are different", from != to);
            }

            @Override
            public void onDiceRolled(int d1, int d2) {
                assertTrue("dices should be 4", d1 == 4 && d2 == 4);
            }
        });
        game.endRoundNumberOfTimes(4);
        game.endOfTurn();

        game.moveUnit(oldFrom, oldTo); //see terminal

        CombatStrategy combatStrategy = new AdvancedCombatStrategy(new FixedDiceStrategy(4));
        combatStrategy.startCombat(game, new Position(1,3), new Position(2,3));
    }


}


