package hotciv.framework.interfaces.WinnerStrategy;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created by jrt on 16/11/15.
 */
public interface WinnerStrategy {


    Player getWinner(GameImpl game);
}
