package hotciv.framework.interfaces.UnitStateStrategy.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.UnitState.UnitState;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;

/**
 * Created by smp on 06/12/15.
 */
public class ExtendedUnitStateStrategy implements UnitStateStrategy {
    @Override
    public UnitState getDefaultState(String typeString, Game game) {
        return game.getUnitConstants().getDefaultStates().get(typeString);
    }

    @Override
    public UnitState getAlternateState(String typeString, Game game) {
        return game.getUnitConstants().getAlternateStates().get(typeString);
    }
}
