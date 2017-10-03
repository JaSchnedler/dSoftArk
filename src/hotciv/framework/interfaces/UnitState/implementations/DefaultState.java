package hotciv.framework.interfaces.UnitState.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.UnitState.UnitState;

/**
 * Created by smp on 16/11/15.
 */
public class DefaultState implements UnitState {

    private String typeString;

    public DefaultState(String typeString) {
        this.typeString = typeString;

    }

    @Override
    public int getDefensiveStrength(Game game) {
        return game.getUnitConstants().getDefenseStrengths().get(typeString);
    }

    @Override
    public int getMoveCount(Game game) {
        return game.getUnitConstants().getMoveCounts().get(typeString);
    }

    @Override
    public int getAttackingStrength(Game game) {
        return game.getUnitConstants().getAttackStrengths().get(typeString);
    }

    @Override
    public UnitState changeState(Game game) {
        return game.getUnitConstants().getAlternateStates().get(typeString);
    }
}
