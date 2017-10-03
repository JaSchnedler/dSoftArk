package hotciv.framework.interfaces.DiceStrategy.implementations;

import hotciv.framework.interfaces.DiceStrategy.DiceStrategy;

/**
 * Created by smp on 26/11/15.
 */
public class FixedDiceStrategy implements DiceStrategy {
    private final int fixedValue;

    public FixedDiceStrategy(int fixedValue) {
        this.fixedValue = fixedValue;
    }

    @Override
    public int rollDice() {
        return fixedValue;
    }
}
