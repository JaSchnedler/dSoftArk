package hotciv.framework.interfaces.WorldStrategy.implementations.adapters;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Tile;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldUtility;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smp on 11/12/15.
 * Purpose: translate the World Layout from a String
 */
public class ThirdPartyWorldAdapter implements WorldStrategy {
    @Override
    public void createWorld(Game game) {
        ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();

        String[] worldLayout = new String[16*16];

        String row = "";

        for ( int r = 0; r < 16; r++ ) {
            for ( int c = 0; c < 16; c++ ) {
                row += generator.getLandscapeAt(r,c) + "";
            }
            worldLayout[r] = row;
            row = "";
        }

        game.getTiles().putAll(WorldUtility.defineWorld(worldLayout));
    }
}
