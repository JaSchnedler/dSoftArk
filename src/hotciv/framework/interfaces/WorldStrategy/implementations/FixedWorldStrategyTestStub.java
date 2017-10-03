package hotciv.framework.interfaces.WorldStrategy.implementations;

import hotciv.framework.*;
import hotciv.framework.interfaces.*;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.DefaultUnitStateStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldUtility;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by smp on 26/11/15.
 */
public class FixedWorldStrategyTestStub implements WorldStrategy {
    @Override
    public void createWorld(Game game) {
        UnitStateStrategy unitStateStrategy = new DefaultUnitStateStrategy();

        game.getUnits().put(new Position(1, 2), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(1, 3), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(1, 4), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(0, 1), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(0, 2), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(0, 3), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));

        game.getUnits().put(new Position(2, 1), new UnitImpl(Player.RED, GameConstants.LEGION, unitStateStrategy, game));
        game.getUnits().put(new Position(2, 2), new UnitImpl(Player.RED, GameConstants.LEGION, unitStateStrategy, game));
        game.getUnits().put(new Position(2, 3), new UnitImpl(Player.RED, GameConstants.LEGION, unitStateStrategy, game));


        Position c1 = new Position(8,8);
        game.getCities().put(c1, new CityImpl(Player.RED, c1));

        // everything is Plains
        String[] worldLayout = new String[] {
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooMoooooo",
                "ooooooo.oMoooooo",
                "ooooooo.ffoooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
                "oooooooooooooooo",
        };

        game.getTiles().putAll(WorldUtility.defineWorld(worldLayout));

    }
}
