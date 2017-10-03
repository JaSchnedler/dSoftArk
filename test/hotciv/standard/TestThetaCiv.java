package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.interfaces.City;
import hotciv.framework.interfaces.Unit;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.ExtendedUnitStateStrategy;
import hotciv.variations.thetaciv.ThetaFactory;
import hotciv.framework.interfaces.UnitConstantsStrategy.implementations.ThetaUnitConstants;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by jrt on 06/12/15.
 */
public class TestThetaCiv {

    private GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
    }

    @Test
    public void chariotShouldExist(){

        game.getUnits().put(new Position(9,9), new UnitImpl(Player.BLUE, ThetaUnitConstants.CHARIOT, new ExtendedUnitStateStrategy(), game));

        assertThat("Unit at position 9,9 should be a chariot", game.getUnitAt(new Position(9,9)).getTypeString(), is(ThetaUnitConstants.CHARIOT));
    }

    @Test
    public void shouldAllowChariotToFortify(){
        // There is an chariot at (2,0)
        Position chariotPosition = new Position(9,9);
        game.getUnits().put(chariotPosition, new UnitImpl(Player.RED, ThetaUnitConstants.CHARIOT, new ExtendedUnitStateStrategy(), game));

        Unit chariot = game.getUnitAt(chariotPosition);

        // What is the regular defense strength?
        int regularDefenseStrength = chariot.getDefensiveStrength();

        // The chariot is allowed to move when it is NOT fortified
        assertTrue("should have moves left in default state", chariot.getMoveCount() > 0);

        // Perform Unit Action should change the state of the Archer to Fortified
        game.performUnitActionAt(chariotPosition);

        // Did that happen? If so, the defense strength should be doubled
        int fortifiedDefenseStrength = chariot.getDefensiveStrength();
        assertThat("Fortified Defense Strength should be the double of the regular", fortifiedDefenseStrength, is(2 * regularDefenseStrength));

        // The chariot is not allowed to move when it is fortified -- is that true?
        assertThat("should not have any moves left in fortified state", chariot.getMoveCount(), is(0));

        // When the chariot fortifies again it leaves its fortified state, after which its defense strength should return to default
        game.performUnitActionAt(chariotPosition);
        int finalDefenseStrength = chariot.getDefensiveStrength();
        assertThat("The defense strength should be back to normal", finalDefenseStrength, is(regularDefenseStrength));
    }

    @Test
    public void shouldAllowCitiesToProduceChariots(){
        Position cityPos = new Position(1,1);

        City city = game.getCityAt(cityPos);
        game.changeProductionInCityAt(cityPos, ThetaUnitConstants.CHARIOT);

        game.endRoundNumberOfTimes(20);
        Unit unit = city.produceUnit(game);
        assertNotNull("City should be allowed to produce a unit", unit);
        assertThat("Unit should be of type CHARIOT", unit.getTypeString(), is(ThetaUnitConstants.CHARIOT));
    }

    @Test
    public void shouldHaveValidAttributesForChariot(){
        UnitImpl chariot = new UnitImpl(Player.RED, ThetaUnitConstants.CHARIOT, new ExtendedUnitStateStrategy(), game);

        assertThat("Chariot should have 1 move count", chariot.getMoveCount(), is(1));
        assertThat("Chariot should have 1 defense strength", chariot.getDefensiveStrength(), is(1));
        assertThat("Chariot should have 3 attack strength", chariot.getAttackingStrength(), is(3));
        assertThat("Chariot should cost 20", game.getUnitConstants().getUnitCosts().get(chariot.getTypeString()), is(20));

    }

}
