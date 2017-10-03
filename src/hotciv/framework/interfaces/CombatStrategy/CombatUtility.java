package hotciv.framework.interfaces.CombatStrategy;

import hotciv.framework.*;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Tile;
import hotciv.framework.interfaces.WorldStrategy.WorldUtility;

import java.util.*;

/** Collection of utility methods for speeding up 
 * the implementation effort in the HotCiv Game
<#if type == "code">

<#include "/data/author.txt">
</#if>
*/
public class CombatUtility {
  
  /** get the terrain factor for the attack and defense strength
   * according to the GammaCiv specification
   * @param game the game the factor should be given for
   * @param position the position that the factor should be calculated
   * for
   * @return the terrain factor
   */
  public static int getTerrainFactor(Game game, Position position) {
    // cities overrule underlying terrain
    if ( game.getCityAt(position) != null ) { return 3; }
    Tile t = game.getTileAt(position);
    if ( t.getTypeString().equals(GameConstants.FOREST) ||
        t.getTypeString().equals(GameConstants.HILLS) ) {
      return 2;
    } 
    return 1;
  }
  
  /** calculate the additional support a unit at position p owned by a 
   * given player gets from friendly units on the given game.
   * @param game the game to calculate on
   * @param position the position of the unit whose support is wanted
   * @param player the player owning the unit at position 'position'
   * @return the support for the unit, +1 for each friendly unit in the 8
   * neighborhood.
   */
  public static int getFriendlySupport(Game game, Position position, 
                                       Player player) {
    Iterator<Position> neighborhood = WorldUtility.get8NeighborhoodIterator(position);
    Position p; 
    int support = 0;
    while ( neighborhood.hasNext() ) {
      p = neighborhood.next();
      if ( game.getUnitAt(p) != null && 
          game.getUnitAt(p).getOwner() == player ) {
        support++;
      }
    }
    return support;
  }
}