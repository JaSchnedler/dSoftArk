package hotciv.framework.interfaces.CombatStrategy.implementations;

import hotciv.framework.interfaces.Game;
import hotciv.framework.Position;
import hotciv.framework.interfaces.Unit;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.DiceStrategy.DiceStrategy;
import hotciv.framework.interfaces.CombatStrategy.CombatUtility;
import hotciv.standard.GameImpl;

/**
 * Created by smp on 26/11/15.
 */
public class AdvancedCombatStrategy implements CombatStrategy {

    private DiceStrategy diceStrategy;

    public AdvancedCombatStrategy(DiceStrategy diceStrategy){
        this.diceStrategy = diceStrategy;
    }

    @Override
    public boolean startCombat(Game game, Position attacker, Position defender) {
        Unit attackingUnit = game.getUnitAt(attacker);
        Unit defendingUnit = game.getUnitAt(defender);

        int attackStrength = (attackingUnit.getAttackingStrength() + CombatUtility.getFriendlySupport(game, attacker, attackingUnit.getOwner())) * CombatUtility.getTerrainFactor(game, attacker);
        int d1 = diceStrategy.rollDice();

        int defenseStrength = (defendingUnit.getDefensiveStrength() + CombatUtility.getFriendlySupport(game, defender, defendingUnit.getOwner())) * CombatUtility.getTerrainFactor(game, defender);
        int d2 = diceStrategy.rollDice();

        ((GameImpl)game).notifyDiceRolled(d1,d2);

        int finalAttack = attackStrength * d1;
        int finalDefense = defenseStrength * d2;


        return finalAttack > finalDefense;
    }
}
