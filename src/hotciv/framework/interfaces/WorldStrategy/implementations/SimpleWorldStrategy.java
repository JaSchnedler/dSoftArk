package hotciv.framework.interfaces.WorldStrategy.implementations;

import hotciv.framework.*;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.DefaultUnitStateStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by smp on 16/11/15.
 */
public class SimpleWorldStrategy implements WorldStrategy {
    @Override
    public void createWorld(Game game) {

        game.getPlayers().add(Player.BLUE);
        game.getPlayers().add(Player.RED);

        game.getCities().put(new Position(1, 1), new CityImpl(Player.RED, new Position(1, 1)));
        game.getCities().put(new Position(4, 1), new CityImpl(Player.BLUE, new Position(4, 1)));

        UnitStateStrategy unitStateStrategy = new DefaultUnitStateStrategy();

        game.getUnits().put(new Position(2, 0), new UnitImpl(Player.RED, GameConstants.ARCHER, unitStateStrategy, game));
        game.getUnits().put(new Position(4, 3), new UnitImpl(Player.RED, GameConstants.SETTLER, unitStateStrategy, game));
        game.getUnits().put(new Position(3, 2), new UnitImpl(Player.BLUE, GameConstants.LEGION, unitStateStrategy, game));
        game.getUnits().put(new Position(0, 0), new UnitImpl(Player.BLUE, GameConstants.ARCHER, unitStateStrategy, game));

        game.getTiles().put(new Position(0, 0), new TileImpl(GameConstants.PLAINS));
        game.getTiles().put(new Position(0, 1), new TileImpl(GameConstants.MOUNTAINS));
        game.getTiles().put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
    }
}
