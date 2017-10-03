package hotciv.framework.interfaces.StateChangeStrategy.implementations;

import hotciv.framework.interfaces.GameState.GameState;
import hotciv.standard.GameImpl;
import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;

/**
 * Created by smp on 27/11/15.
 */
public class DelayedStateChangeStrategy implements StateChangeStrategy {

    private GameImpl game;

    public void setGame(GameImpl game){
        this.game = game;
    }

    @Override
    public GameState changeStateIfPossible() {
        if(game.getRoundsPassed() >= 20){
            if(game.getState() == game.getDefaultState()){
                return game.getAlternateState();
            }
        }
        return game.getDefaultState();

    }
}
