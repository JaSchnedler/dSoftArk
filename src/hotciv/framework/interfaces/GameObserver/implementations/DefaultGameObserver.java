package hotciv.framework.interfaces.GameObserver.implementations;

import hotciv.framework.Position;
import hotciv.framework.interfaces.GameObserver.GameObserver;
import hotciv.framework.interfaces.Unit;

/**
 * Created by Jacob on 11-12-2015.
 */
public class DefaultGameObserver implements GameObserver {
    @Override
    public void onRoundEnded(Integer roundsPassed) {
        System.out.println("roundsPassed = " + roundsPassed);
    }

    @Override
    public void onUnitMoved(Unit unitAt, Position from, Position to, Boolean validity) {
        System.out.println("unitAt = [" + unitAt.getOwner() +  " " + unitAt.getTypeString() + "], from = [" + from + "], to = [" + to + "], validity = [" + validity + "]");
    }

    @Override
    public void onDiceRolled(int d1, int d2) {
        System.out.println("d1 = [" + d1 + "], d2 = [" + d2 + "]");
    }
}
