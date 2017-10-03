package hotciv.framework.interfaces.AttackCountStrategy.implementations;

import hotciv.framework.Player;
import hotciv.framework.interfaces.AttackCountStrategy.AttackCountStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public class IncrementalAttackCountStrategy implements AttackCountStrategy {

    private int blueCounter;
    private int redCounter;

    @Override
    public void incrementAttackCounter(GameImpl game) {
        if(game.getPlayerInTurn() == Player.RED){
            redCounter++;
        } else {
            blueCounter++;
        }
    }

    @Override
    public int getBlueCount() {
        return blueCounter;
    }

    @Override
    public int getRedCount() {
        return redCounter;
    }
}
