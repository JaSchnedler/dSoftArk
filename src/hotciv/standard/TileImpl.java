package hotciv.standard;

import hotciv.framework.interfaces.Tile;

/**
 * Created by jrt on 02/11/15.
 */
public class TileImpl implements Tile {

    private String typeString;

    public TileImpl(String typeString) {
        this.typeString = typeString;
    }

    /**
     * return the tile type as a string. The set of
     * valid strings are defined by the graphics
     * engine, as they correspond to named image files.
     *
     * @return the type type as string
     */
    @Override
    public String getTypeString() {
        return typeString;
    }
}
