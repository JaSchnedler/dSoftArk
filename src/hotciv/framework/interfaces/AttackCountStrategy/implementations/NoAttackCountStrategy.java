package hotciv.framework.interfaces.AttackCountStrategy.implementations;

import hotciv.framework.interfaces.AttackCountStrategy.AttackCountStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public class NoAttackCountStrategy implements AttackCountStrategy {
    @Override
    public void incrementAttackCounter(GameImpl game) {

    }

    @Override
    public int getBlueCount() {
        return 0;
    }

    @Override
    public int getRedCount() {
        return 0;
    }
}
