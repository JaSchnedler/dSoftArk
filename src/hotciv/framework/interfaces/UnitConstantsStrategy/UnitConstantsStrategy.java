package hotciv.framework.interfaces.UnitConstantsStrategy;

import hotciv.framework.interfaces.UnitState.UnitState;

import java.util.List;
import java.util.Map;

/**
 * Created by smp on 06/12/15.
 */
public interface UnitConstantsStrategy {
    List<String> getAvailableUnitTypes();

    Map<String, Integer> getUnitCosts();

    Map<String, Integer> getMoveCounts();

    Map<String, Integer> getAttackStrengths();

    Map<String, Integer> getDefenseStrengths();

    Map<String, UnitState> getDefaultStates();

    Map<String, UnitState> getAlternateStates();
}
