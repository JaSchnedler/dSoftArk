package hotciv.framework.interfaces.UnitConstantsStrategy.implementations;

import hotciv.framework.interfaces.UnitConstantsStrategy.UnitConstantsStrategy;
import hotciv.framework.interfaces.UnitState.UnitState;
import hotciv.framework.interfaces.UnitState.implementations.DefaultState;
import hotciv.framework.interfaces.UnitState.implementations.FortifiedState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jrt on 06/12/15.
 */
public class ThetaUnitConstants implements UnitConstantsStrategy {

    public static final String CHARIOT = "chariot";

    DefaultUnitConstants unitConstants = new DefaultUnitConstants();

    @Override
    public List<String> getAvailableUnitTypes() {
        List<String> list = new ArrayList<>();
        list.addAll(unitConstants.getAvailableUnitTypes());
        list.add(CHARIOT);

        return list;
    }

    @Override
    public Map<String, Integer> getUnitCosts() {

        Map<String, Integer> unitCosts = new HashMap<>();

        unitCosts.putAll(unitConstants.getUnitCosts());
        unitCosts.put(CHARIOT, 20);

        return unitCosts;
    }

    @Override
    public Map<String, Integer> getMoveCounts() {

        Map<String, Integer> unitMoves = new HashMap<>();

        unitMoves.putAll(unitConstants.getMoveCounts());
        unitMoves.put(CHARIOT, 1);

        return unitMoves;
    }

    @Override
    public Map<String, Integer> getAttackStrengths() {

        Map<String, Integer> unitStrengths = new HashMap<>();

        unitStrengths.putAll(unitConstants.getAttackStrengths());
        unitStrengths.put(CHARIOT, 3);

        return unitStrengths;
    }

    @Override
    public Map<String, Integer> getDefenseStrengths() {

        Map<String, Integer> unitDefenses = new HashMap<>();

        unitDefenses.putAll(unitConstants.getDefenseStrengths());
        unitDefenses.put(CHARIOT, 1);

        return unitDefenses;
    }

    @Override
    public Map<String, UnitState> getDefaultStates() {

        Map<String, UnitState> defaultStates = new HashMap<>();

        defaultStates.putAll(unitConstants.getDefaultStates());
        defaultStates.put(ThetaUnitConstants.CHARIOT, new DefaultState(ThetaUnitConstants.CHARIOT));

        return defaultStates;
    }

    @Override
    public Map<String, UnitState> getAlternateStates() {

        Map<String, UnitState> alternateStates = new HashMap<>();

        alternateStates.putAll(unitConstants.getAlternateStates());
        alternateStates.put(ThetaUnitConstants.CHARIOT, new FortifiedState(ThetaUnitConstants.CHARIOT));

        return alternateStates;
    }

}
