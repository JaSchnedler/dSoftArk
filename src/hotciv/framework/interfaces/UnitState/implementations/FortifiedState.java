package hotciv.framework.interfaces.UnitState.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.UnitState.UnitState;

/**
 * Created by smp on 16/11/15.
 */
public class FortifiedState implements UnitState {
    private String typeString;

    public FortifiedState(String typeString) {
        this.typeString = typeString;
    }

    @Override
    public int getDefensiveStrength(Game game) {
        return game.getUnitConstants().getDefenseStrengths().get(typeString) * 2;
    }

    @Override
    public int getMoveCount(Game game) {
        return 0;
    }

    @Override
    public int getAttackingStrength(Game game) {
        return game.getUnitConstants().getAttackStrengths().get(typeString);
    }

    @Override
    public UnitState changeState(Game game) { return game.getUnitConstants().getDefaultStates().get(typeString); }
}
