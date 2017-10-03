package hotciv.framework.interfaces.WorldStrategy;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Tile;
import hotciv.standard.TileImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by smp on 27/11/15.
 */
public class WorldUtility {
    /** Define the world as the DeltaCiv layout
     * */
    public static Map<Position,Tile> defineWorld(String[] layout) {
        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.

        // Conversion...
        Map<Position,Tile> theWorld = new HashMap<Position,Tile>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put( p, new TileImpl(type));
            }
        }
        return theWorld;
    }

    /** return an iterator over positions that are in the 8 neighborhood
     * of a given position. The 8 neighborhood is the (normally 8)
     * positions adjacent to the center position. The center position
     * itself is not part of the iterator. PostCondition: The iterator
     * is always valid and will contain from 3 to 8 positions. It will
     * only contain positions that are valid on the game world, that is
     * a neighborhood centered in (0,0) will contain (1,0), (1,1) and
     * (0,1) but not e.g. (-1,-1). The iterator always return the
     * positions in a sequence starting at the north west position and
     * the rest given row-wise.
     * @param center the position marking the center of the 8 neighborhood.
     * @return iterator over the valid positions in the center's 8
     * neighborhood.
     */
    public static Iterator<Position> get8NeighborhoodIterator(Position center) {
        ArrayList<Position> list = new ArrayList<Position>();
        int row = center.getRow(); int col = center.getColumn();
        int r,c;
        for (int dr = -1; dr <= +1; dr++) {
            for (int dc = -1; dc <= +1; dc++) {
                r = row+dr; c = col+dc;
                // avoid positions outside the world
                if ( r >= 0 && r < GameConstants.WORLDSIZE &&
                        c >= 0 && c < GameConstants.WORLDSIZE ) {
                    // avoid center position
                    if ( r != row || c != col ){
                        list.add( new Position(r,c));
                    }
                }
            }
        }
        return list.iterator();
    }
}
