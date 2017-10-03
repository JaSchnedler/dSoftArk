package hotciv.framework.interfaces.DiceStrategy.implementations;

import hotciv.framework.interfaces.DiceStrategy.DiceStrategy;

import java.util.Random;

/**
 * Created by smp on 26/11/15.
 */
public class RandomDiceStrategy implements DiceStrategy {
    @Override
    public int rollDice() {
        return 1 + new Random().nextInt(6);
    }
}
