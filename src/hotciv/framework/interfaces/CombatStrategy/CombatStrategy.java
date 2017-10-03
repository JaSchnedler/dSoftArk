package hotciv.framework.interfaces.CombatStrategy;

import hotciv.framework.Position;
import hotciv.framework.interfaces.Game;

/**
 * Created by smp on 26/11/15.
 */
public interface CombatStrategy {
    boolean startCombat(Game game, Position from, Position to);
}
