package hotciv.framework.interfaces;

import hotciv.framework.interfaces.UnitConstantsStrategy.UnitConstantsStrategy;
import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;

/**
 * Created by jrt on 27/11/15.
 */
public interface GameFactory {

    WorldStrategy createWorldStrategy();

    AgeStrategy createAgeStrategy();

    UnitActionStrategy createUnitActionStrategy();

    CombatStrategy createCombatStrategy();

    GameState createDefaultGameState();

    GameState createAlternateGameState();

    StateChangeStrategy createStateChangeStrategy();

    ResourceStrategy createResourceStrategy();

    UnitStateStrategy createUnitStateStrategy();

    UnitConstantsStrategy createUnitConstantsStrategy();
}
