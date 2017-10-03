package hotciv.standard;

import hotciv.variations.epsilonciv.*;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.CombatStrategy.implementations.AdvancedCombatStrategy;
import hotciv.framework.interfaces.DiceStrategy.implementations.FixedDiceStrategy;
import hotciv.framework.interfaces.WinnerStrategy.WinnerStrategy;
import hotciv.framework.interfaces.WinnerStrategy.implementations.AdvancedWinnerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by jrt on 26/11/15.
 */
public class TestEpsilonCiv {

    private GameImpl game;

    @Before
    public void setUp() {

        game = new GameImpl(new EpsilonFactory());
    }

    @Test
    public void shouldHavWinnerToBeSuccessfulAttacker(){
        game.endOfTurn();
        boolean validAttack1 = game.moveUnit(new Position(1, 2), new Position(2,1));
        assertTrue("Attack 1 is successful", validAttack1);

        boolean validAttack2 = game.moveUnit(new Position(1, 3), new Position(2,2));
        assertTrue("Attack 2 is successful", validAttack2);

        boolean validAttack3 = game.moveUnit(new Position(1, 4), new Position(2, 3));
        assertTrue("Attack 3 is successful", validAttack3);

        assertThat("Winner should be blue", game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldAtttackBasedOnAlgorithm(){
        Position attacker = new Position(1,2);
        Position defender = new Position(2,1);

        CombatStrategy cs = new AdvancedCombatStrategy(new FixedDiceStrategy(3));

        boolean attackerWins = cs.startCombat(game, attacker, defender);


        assertTrue("Attacker is winner", attackerWins);
    }

}


