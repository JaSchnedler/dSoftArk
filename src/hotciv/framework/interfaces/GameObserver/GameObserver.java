package hotciv.framework.interfaces.GameObserver;

import hotciv.framework.Position;
import hotciv.framework.interfaces.Unit;

/**
 * Created by Jacob on 11-12-2015.
 */
public interface GameObserver {

    void onRoundEnded(Integer roundsPassed);

    void onUnitMoved(Unit unitAt, Position from, Position to, Boolean validity);

    void onDiceRolled(int d1, int d2);
}
