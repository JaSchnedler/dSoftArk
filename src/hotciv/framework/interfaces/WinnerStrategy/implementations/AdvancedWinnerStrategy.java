package hotciv.framework.interfaces.WinnerStrategy.implementations;

import hotciv.framework.Player;
import hotciv.framework.interfaces.WinnerStrategy.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by jrt on 26/11/15.
 */
public class AdvancedWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(GameImpl game) {

        int blueAttackCount = game.getAttackCountStrategy().getBlueCount();
        int redAttackCount = game.getAttackCountStrategy().getRedCount();

        if(blueAttackCount > redAttackCount && blueAttackCount > 2){
            return Player.BLUE;
        }
        else if(redAttackCount > blueAttackCount && redAttackCount > 2){
            return Player.RED;
        }
        return null;
    }
}
