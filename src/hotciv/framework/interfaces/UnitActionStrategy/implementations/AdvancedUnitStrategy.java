package hotciv.framework.interfaces.UnitActionStrategy.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Unit;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.framework.interfaces.UnitConstantsStrategy.implementations.ThetaUnitConstants;

/**
 * Created by smp on 26/11/15.
 */
public class AdvancedUnitStrategy implements UnitActionStrategy {
    @Override
    public void performUnitAction(Game game, Position p) {
        Unit unit = game.getUnitAt(p);

        if(unit.getTypeString().equals(GameConstants.SETTLER)){
            game.getCities().put(p, new CityImpl(unit.getOwner(), p));
            game.getUnits().remove(unit);
        }

        if(unit.getTypeString().equals(GameConstants.ARCHER)){
            ((UnitImpl) unit).changeState();
        }

        if(unit.getTypeString().equals(ThetaUnitConstants.CHARIOT)){ // TODO: Refactor to compositional design
            ((UnitImpl) unit).changeState();
        }
    }
}
