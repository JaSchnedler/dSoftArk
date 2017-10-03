package hotciv.framework.interfaces.AttackCountStrategy.implementations;

import hotciv.framework.interfaces.AttackCountStrategy.AttackCountStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public class DelayedAttackCountStrategy implements AttackCountStrategy {

    private final AttackCountStrategy noCountAttackStrategy;
    private final AttackCountStrategy defaultAttackCountStrategy;
    private int blueCount;
    private int redCount;

    public DelayedAttackCountStrategy(){

        this.defaultAttackCountStrategy = new IncrementalAttackCountStrategy();
        this.noCountAttackStrategy = new NoAttackCountStrategy();
    }

    @Override
    public void incrementAttackCounter(GameImpl game) {
        if(game.getRoundsPassed() >= 20){
            defaultAttackCountStrategy.incrementAttackCounter(game);

            blueCount = defaultAttackCountStrategy.getBlueCount();
            redCount = defaultAttackCountStrategy.getRedCount();
        } else {
            noCountAttackStrategy.incrementAttackCounter(game);

            blueCount = noCountAttackStrategy.getBlueCount();
            redCount = noCountAttackStrategy.getRedCount();
        }
    }

    @Override
    public int getBlueCount() {
        return blueCount;
    }

    @Override
    public int getRedCount() {
        return redCount;
    }
}
