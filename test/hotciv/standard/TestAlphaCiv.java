package hotciv.standard;

import hotciv.variations.alphaciv.*;
import hotciv.framework.*;

import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.Game;
import hotciv.framework.interfaces.Tile;
import hotciv.framework.interfaces.Unit;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private Game game;
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {

      game = new GameImpl(new AlphaFactory());
  }


  @Test
  public void shouldHaveRedCityAt1_1() {
    City c = game.getCityAt(new Position(1,1));
    assertThat("There should be a city at (1,1)",
               c, is(notNullValue()));

    Player p = c.getOwner();
    assertThat("The city should be owned by RED player",
               p, is(Player.RED));
  }

    @Test
    public void shouldHaveRedAsFirstPlayer() {
        assertThat("Red is first player",
                game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldHaveRedAsWinnerInYear3000() {

        // Year 4000
        game.endRoundNumberOfTimes(10);
        // Year 3000

        assertThat("Year is 3000 BC", game.getAge(), is(-3000));
        assertThat("Red is winner",game.getWinner(), is(Player.RED));
    }
    @Test
    public void shouldHaveBlueAsPlayerInTurnAfterRed(){
        game.endOfTurn();
        assertThat("After Red's turn it is blue player in turn",
                game.getPlayerInTurn(), is(Player.BLUE));
    }
    @Test
    public void shouldHaveOceanAt1_0(){
        Tile t = game.getTileAt(new Position(1, 0));
        assertThat("There is ocean at (1,0)", t.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldNotAllowRedToMoveBlueUnit(){

        assertThat("It is RED's turn", game.getPlayerInTurn(), is(Player.RED));

        Unit unit = game.getUnitAt(new Position(0,0));

        assertThat("Unit's owner is BLUE", unit.getOwner(), is(Player.BLUE));

        boolean allowed = game.moveUnit(new Position(0, 0), new Position(2, 2));

        assertThat("Move should not be allowed", allowed, is(false));

    }

    @Test
    public void shouldNotAllowUnitToMoveOverMountains() {

        Tile t1 = game.getTileAt(new Position(0, 0));
        Tile t2 = game.getTileAt(new Position(0, 1));

        assertThat("t1 tile should be plains", t1.getTypeString(), is(GameConstants.PLAINS));
        assertThat("t2 tile should be mountain", t2.getTypeString(), is(GameConstants.MOUNTAINS));

        boolean allowed = game.moveUnit(new Position(0, 0), new Position(0, 1));

        assertThat("should not allow unit to move over mountains", allowed, is(false));
    }

    @Test
    public void shouldHaveExactlyTwoPlayersRedBlue(){

        ArrayList<Player> players = game.getPlayers();

        assertThat("The number of players should be exactly 2", players.size(), is(2));

        assertNotNull("We can make a RED player", players.contains(Player.RED));
        assertNotNull("We can make a BLUE player", players.contains(Player.BLUE));

    }

    @Test
    public void shouldAdvanceBy100YearsInEachRound(){
        int roundStartAge = game.getAge();

        game.endOfTurn(); //Red starts. End of turn.
        game.endOfTurn(); //Round is finished.

        int roundEndAge = game.getAge();

        assertThat("Difference between startage and endage is 100 years", roundEndAge - roundStartAge, is(100));

    }

    @Test
    public void shouldStartGameAt4000BC(){
        assertThat("The game starts in 4000 BC", game.getAge(),is(-4000));

    }
    @Test
    public void shouldChangePlayerInTurnOnEndTurn(){
        //We know from above that Red starts
        game.endOfTurn();
        assertThat("Blue is after Red", game.getPlayerInTurn(), is(Player.BLUE));
        game.endOfTurn();
        assertThat("Red is after Blue", game.getPlayerInTurn(), is(Player.RED));

    }
    @Test
    public void shouldHaveBlueCityAt4_1(){

        City blueCity = game.getCityAt(new Position(4, 1));

        assertThat("There is a blue city at (4,1)",blueCity.getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldBeARedArcherAt2_0(){

        Unit redArcher = game.getUnitAt(new Position(2, 0));

        assertThat("There is a red unit at (2,0)",redArcher.getOwner(),is(Player.RED));

        assertThat("The unit is an archer",redArcher.getTypeString(),is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBeRedASettlerAt4_3(){

        Unit redSettler = game.getUnitAt(new Position(4,3));
        assertThat("There is a red unit at 4,3", redSettler.getOwner(), is(Player.RED));
        assertThat("The unit is a settler",redSettler.getTypeString(),is(GameConstants.SETTLER));
    }

    @Test
    public void shouldHaveABlueLegionAt3_2(){
        Unit blueLegion = game.getUnitAt(new Position(3, 2));
        assertThat("There is a unit at 3,2", blueLegion.getOwner(),is(Player.BLUE));
        assertThat("The unit is a legion", blueLegion.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldAllowCityToProduceUnit(){
        City city = game.getCityAt(new Position(1, 1));
        game.endRoundNumberOfTimes(20);
        Unit unit = city.produceUnit(game);
        assertNotNull("City should be allowed to produce a unit", unit);
    }

    @Test
    public void shouldAllowToSetCityProduction(){
        CityImpl city = new CityImpl(Player.RED, null);
        city.setProduction(GameConstants.LEGION);
        assertThat("The produced unit should be a legion", city.getProduction(), is(GameConstants.LEGION));

        city.setProduction(GameConstants.ARCHER);
        assertThat("The produced unit should be an archer", city.getProduction(), is(GameConstants.ARCHER));
    }
    @Test
    public void shouldKeepCityPopulationAt1(){
        City city = game.getCityAt(new Position(1, 1));
        game.endRoundNumberOfTimes(30);
        assertThat("The city should stay at pop size 1 ",city.getSize(),is(1));

    }
    @Test
    public void citiesProduce6EachRound(){
        City city = game.getCityAt(new Position(1,1));
        int initialProduction = city.getProductionCount();
        game.endRoundNumberOfTimes(1);
        assertThat("The production for the city has risen", city.getProductionCount(), is(initialProduction + 6));

    }
    @Test
    public void citiesLooseProductionFromProducingAUnit(){
        City city = game.getCityAt(new Position(1, 1));
        game.endRoundNumberOfTimes(10);
        int initialProduction = city.getProductionCount();
        city.produceUnit(game);
        assertThat("The city has lost ressources", city.getProductionCount(),is(initialProduction-10));

    }

}


































