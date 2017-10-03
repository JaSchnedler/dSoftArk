package hotciv.framework.interfaces.CombatStrategy.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.Position;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;

/**
 * Created by smp on 26/11/15.
 */
public class AttackerWinsCombatStrategy implements CombatStrategy {
    @Override
    public boolean startCombat(Game game, Position from, Position to) {
        return true;
    }
}
