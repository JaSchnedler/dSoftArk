package hotciv.framework.interfaces.UnitConstantsStrategy.implementations;

import hotciv.framework.GameConstants;
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
public class DefaultUnitConstants implements UnitConstantsStrategy {

    @Override
    public List<String> getAvailableUnitTypes() {
        List<String> list = new ArrayList<>();
        list.add(GameConstants.ARCHER);
        list.add(GameConstants.LEGION);
        list.add(GameConstants.SETTLER);

        return list;
    }

    @Override
    public Map<String, Integer> getUnitCosts() {

        Map<String, Integer> unitCosts = new HashMap<>();

        unitCosts.put(GameConstants.ARCHER, 10);
        unitCosts.put(GameConstants.LEGION, 15);
        unitCosts.put(GameConstants.SETTLER, 30);

        return unitCosts;
    }

    @Override
    public Map<String, Integer> getMoveCounts() {

        Map<String, Integer> unitMoves = new HashMap<>();

        unitMoves.put(GameConstants.ARCHER, 1);
        unitMoves.put(GameConstants.LEGION, 1);
        unitMoves.put(GameConstants.SETTLER, 1);

        return unitMoves;
    }

    @Override
    public Map<String, Integer> getAttackStrengths() {

        Map<String, Integer> unitStrengths = new HashMap<>();

        unitStrengths.put(GameConstants.ARCHER, 2);
        unitStrengths.put(GameConstants.LEGION, 4);
        unitStrengths.put(GameConstants.SETTLER, 0);

        return unitStrengths;
    }

    @Override
    public Map<String, Integer> getDefenseStrengths() {

        Map<String, Integer> unitDefenses = new HashMap<>();

        unitDefenses.put(GameConstants.ARCHER, 3);
        unitDefenses.put(GameConstants.LEGION, 2);
        unitDefenses.put(GameConstants.SETTLER, 3);

        return unitDefenses;
    }

    @Override
    public Map<String, UnitState> getDefaultStates() {

        Map<String, UnitState> defaultStates = new HashMap<>();

        defaultStates.put(GameConstants.ARCHER, new DefaultState(GameConstants.ARCHER));
        defaultStates.put(GameConstants.LEGION, new DefaultState(GameConstants.LEGION));
        defaultStates.put(GameConstants.SETTLER, new DefaultState(GameConstants.SETTLER));

        return defaultStates;
    }

    @Override
    public Map<String, UnitState> getAlternateStates() {

        Map<String, UnitState> alternateStates = new HashMap<>();

        alternateStates.put(GameConstants.ARCHER, new FortifiedState(GameConstants.ARCHER));
        alternateStates.put(GameConstants.LEGION, new DefaultState(GameConstants.LEGION));
        alternateStates.put(GameConstants.SETTLER, new DefaultState(GameConstants.SETTLER));

        return alternateStates;
    }
}
