package hotciv.framework.interfaces.UnitState;

import hotciv.framework.interfaces.Game;

/**
 * Created by smp on 16/11/15.
 */
public interface UnitState {
    /** return the defensive strength of this unit
     * @return defensive strength
     * @param game
     */
    int getDefensiveStrength(Game game);

    /** return the move distance left (move count).
     * A move count of N means the unit may travel
     * a distance of N in this turn. The move count
     * is reset to the units maximal value before
     * a new turn starts.
     * @return the move count
     * @param game
     */
    int getMoveCount(Game game);

    int getAttackingStrength(Game game);

    UnitState changeState(Game game);
}
