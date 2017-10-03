package hotciv.framework.interfaces.UnitActionStrategy.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.Position;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;

/**
 * Created by smp on 26/11/15.
 */
public class NoUnitActionStrategy implements UnitActionStrategy {
    @Override
    public void performUnitAction(Game game, Position p) {
        // does nothing
    }
}
