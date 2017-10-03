package hotciv.framework.interfaces.WorldStrategy.implementations;

import hotciv.framework.*;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldUtility;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by smp on 16/11/15.
 */
public class AdvancedWorldStrategy implements WorldStrategy {


    String[] worldLayout = new String[] {
            "...ooMooooo.....",
            "..ohhoooofffoo..",
            ".oooooMooo...oo.",
            ".ooMMMoooo..oooo",
            "...ofooohhoooo..",
            ".ofoofooooohhoo.",
            "...ooo..........",
            ".ooooo.ooohooM..",
            ".ooooo.oohooof..",
            "offfoooo.offoooo",
            "oooooooo...ooooo",
            ".ooMMMoooo......",
            "..ooooooffoooo..",
            "....ooooooooo...",
            "..ooohhoo.......",
            ".....ooooooooo..",
    };


    @Override
    public void createWorld(Game game) {

        game.getTiles().putAll(WorldUtility.defineWorld(worldLayout));

        game.getPlayers().add(Player.BLUE);
        game.getPlayers().add(Player.RED);

        game.getCities().put(new Position(8, 12), new CityImpl(Player.RED, new Position(8, 12)));
        game.getCities().put(new Position(4, 5), new CityImpl(Player.BLUE, new Position(4, 5)));

        game.getUnits().put(new Position(3, 8), new UnitImpl(Player.RED, GameConstants.ARCHER, game.getUnitStateStrategy(), game));
        game.getUnits().put(new Position(4, 4), new UnitImpl(Player.BLUE, GameConstants.LEGION, game.getUnitStateStrategy(), game));

    }
}
