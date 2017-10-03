package hotciv.framework.interfaces.UnitStateStrategy;

import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.UnitState.UnitState;

/**
 * Created by smp on 06/12/15.
 */
public interface UnitStateStrategy {

    UnitState getDefaultState(String typeString, Game game);

    UnitState getAlternateState(String typeString, Game game);
}
