package hotciv.standard;

import hotciv.framework.interfaces.City;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Unit;
import hotciv.variations.gammaciv.GammaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by jrt on 16/11/15.
 */
public class TestGammaCiv {

    private GameImpl game;

    @Before
    public void setUp() {

        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void shouldReplaceSettlerUnitByACityofPopSize1(){
        //Settler to perfom action
        Position settlerPos = new Position(4,3);
        Unit settler = game.getUnitAt(settlerPos);

        game.performUnitActionAt(settlerPos);
        City city = game.getCityAt(settlerPos);
        assertNotNull("There is a city at 4,3", city);
        assertThat("The citys owner is the same as that of the settler", city.getOwner(),is(settler.getOwner()));
        assertThat("The new city has population size 1",city.getSize(),is(1));
    }

    @Test
    public void archerCanFortifyToDoubleDefenseStrengthButNotMove(){
        // There is an archer at (2,0)
        Position archerPosition = new Position(2,0);
        Unit archer = game.getUnitAt(archerPosition);

        // What is the regular defense strength?
        int regularDefenseStrength = archer.getDefensiveStrength();

        // The archer is allowed to move when it is NOT fortified
        assertTrue("should have moves left in default state", archer.getMoveCount() > 0);

        // Perform Unit Action should change the state of the Archer to Fortified
        game.performUnitActionAt(archerPosition);

        // Did that happen? If so, the defense strength should be doubled
        int fortifiedDefenseStrength = archer.getDefensiveStrength();
        assertThat("Fortified Defense Strenght should be the double of the regular", fortifiedDefenseStrength, is(2 * regularDefenseStrength));

        // The archer is not allowed to move when it is fortified -- is that true?
        assertThat("should not have any moves left in fortified state", archer.getMoveCount(), is(0));

        // When the archer fortifies again it leaves its fortified state, after which its defense strength should return to default
        game.performUnitActionAt(archerPosition);
        int finalDefenseStrength = archer.getDefensiveStrength();
        assertThat("The defense strenght should be back to normal", finalDefenseStrength, is(regularDefenseStrength));
    }

}
