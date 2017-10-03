package hotciv.framework.interfaces.StateChangeStrategy;

import hotciv.framework.interfaces.GameState.GameState;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public interface StateChangeStrategy {

    GameState changeStateIfPossible();

    void setGame(GameImpl game);
}
