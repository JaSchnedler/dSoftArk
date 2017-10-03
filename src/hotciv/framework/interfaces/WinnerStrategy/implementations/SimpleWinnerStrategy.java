package hotciv.framework.interfaces.WinnerStrategy.implementations;

import hotciv.framework.Player;
import hotciv.framework.interfaces.WinnerStrategy.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by jrt on 16/11/15.
 */
public class SimpleWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(GameImpl game) {
        return Player.RED;
    }
}
