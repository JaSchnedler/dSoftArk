package hotciv.framework.interfaces.GameState;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public interface GameState {

    Player getWinner(GameImpl game);

    void incrementAttackCounter(GameImpl game);

}
