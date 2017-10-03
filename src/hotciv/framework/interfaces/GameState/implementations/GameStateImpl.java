package hotciv.framework.interfaces.GameState.implementations;

import hotciv.framework.Player;
import hotciv.framework.interfaces.AttackCountStrategy.AttackCountStrategy;
import hotciv.framework.interfaces.WinnerStrategy.WinnerStrategy;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public class GameStateImpl implements GameState {

    private AttackCountStrategy attackCountStrategy;
    private WinnerStrategy winnerStrategy;

    public GameStateImpl(WinnerStrategy winnerStrategy, AttackCountStrategy attackCountStrategy) {
        this.winnerStrategy = winnerStrategy;
        this.attackCountStrategy = attackCountStrategy;
    }

    @Override
    public Player getWinner(GameImpl game) {
        return winnerStrategy.getWinner(game);
    }

    @Override
    public void incrementAttackCounter(GameImpl game) {
        attackCountStrategy.incrementAttackCounter(game);
    }

    public AttackCountStrategy getAttackCountStrategy() {
        return attackCountStrategy;
    }
}
