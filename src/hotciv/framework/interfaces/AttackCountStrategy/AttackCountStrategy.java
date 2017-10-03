package hotciv.framework.interfaces.AttackCountStrategy;

import hotciv.standard.GameImpl;

/**
 * Created by smp on 27/11/15.
 */
public interface AttackCountStrategy {
    void incrementAttackCounter(GameImpl game);
    int getBlueCount();
    int getRedCount();
}
