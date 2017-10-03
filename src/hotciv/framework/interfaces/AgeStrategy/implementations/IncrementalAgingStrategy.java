package hotciv.framework.interfaces.AgeStrategy.implementations;

import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;

/**
 * Created by smp on 09/11/15.
 */
public class IncrementalAgingStrategy implements AgeStrategy {

    @Override
    public int calculateNewAge(int currentAge) {
        return currentAge + 100;
    }
}
