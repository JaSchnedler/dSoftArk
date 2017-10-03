package hotciv.standard.manual;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.standard.GameImpl;
import hotciv.standard.TestFactory;
import hotciv.framework.interfaces.WorldStrategy.implementations.adapters.ThirdPartyWorldAdapter;
/**
 * Created by smp on 11/12/15.
 */

public class TestFractalAdapter {
    /***
     * This method prints the random world layout to the console, as created by the ThirdPartyFractalGenerator.
     * @param args
     */
    public static void main(String args[]){
        Game game = new GameImpl(new TestFactory());
        WorldStrategy worldStrategy = new ThirdPartyWorldAdapter();
        worldStrategy.createWorld(game);

        // Print World as it is "seen" by the Game
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            String line = "";
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                String type = game.getTiles().get(new Position(r,c)).getTypeString();

                if (type.equals(GameConstants.OCEANS)){ line += '.';}
                if (type.equals(GameConstants.PLAINS)){ line += 'o';}
                if (type.equals(GameConstants.MOUNTAINS)){ line += 'M';}
                if (type.equals(GameConstants.FOREST)){ line += 'f';}
                if (type.equals(GameConstants.HILLS)){ line += 'h';}
            }
                System.out.println(line);
        }

    }

}
