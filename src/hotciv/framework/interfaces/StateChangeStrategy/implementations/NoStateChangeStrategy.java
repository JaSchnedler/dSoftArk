package hotciv.framework.interfaces.StateChangeStrategy.implementations;

import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public class NoStateChangeStrategy implements StateChangeStrategy {

    private GameImpl game;

    @Override
    public GameState changeStateIfPossible() {
        return game.getState();
    }

    @Override
    public void setGame(GameImpl game) {
        this.game = game;
    }
}
