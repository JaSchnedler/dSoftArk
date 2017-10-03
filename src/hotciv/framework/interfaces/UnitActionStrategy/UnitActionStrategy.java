package hotciv.framework.interfaces.UnitActionStrategy;

import hotciv.framework.Position;
import hotciv.framework.interfaces.Game;

/**
 * Created by smp on 26/11/15.
 */
public interface UnitActionStrategy {
    void performUnitAction(Game game, Position p);
}
