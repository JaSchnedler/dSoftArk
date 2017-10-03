package hotciv.variations.semiciv;

import hotciv.framework.interfaces.UnitConstantsStrategy.UnitConstantsStrategy;
import hotciv.framework.interfaces.UnitConstantsStrategy.implementations.DefaultUnitConstants;
import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;
import hotciv.framework.interfaces.AgeStrategy.implementations.AdvancedAgingStrategy;
import hotciv.framework.interfaces.AttackCountStrategy.implementations.IncrementalAttackCountStrategy;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.CombatStrategy.implementations.AdvancedCombatStrategy;
import hotciv.framework.interfaces.DiceStrategy.implementations.FixedDiceStrategy;
import hotciv.framework.interfaces.GameFactory;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.framework.interfaces.GameState.implementations.GameStateImpl;
import hotciv.framework.interfaces.ResourceStrategy.implementations.AdvancedResourceStrategy;
import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;
import hotciv.framework.interfaces.StateChangeStrategy.implementations.DelayedStateChangeStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.implementations.AdvancedUnitStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.DefaultUnitStateStrategy;
import hotciv.framework.interfaces.WinnerStrategy.implementations.AdvancedWinnerStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.WorldStrategy.implementations.AdvancedWorldStrategy;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;

/**
 * Created by Jacob on 30-11-2015.
 */
public class SemiFactory implements GameFactory {
    @Override
    public WorldStrategy createWorldStrategy() {
        return new AdvancedWorldStrategy();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new AdvancedAgingStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {return new AdvancedUnitStrategy();}

    @Override
    public CombatStrategy createCombatStrategy() {
        return new AdvancedCombatStrategy(new FixedDiceStrategy(3));
    }

    @Override
    public GameState createDefaultGameState() {
        return new GameStateImpl(new AdvancedWinnerStrategy(), new IncrementalAttackCountStrategy());
    }

    @Override
    public GameState createAlternateGameState() {
        return createDefaultGameState();
    }

    @Override
    public StateChangeStrategy createStateChangeStrategy() {
        return new DelayedStateChangeStrategy();
    }

    @Override
    public ResourceStrategy createResourceStrategy() {
        return new AdvancedResourceStrategy();
    }

    @Override
    public UnitStateStrategy createUnitStateStrategy() {
        return new DefaultUnitStateStrategy();
    }

    @Override
    public UnitConstantsStrategy createUnitConstantsStrategy() {
        return new DefaultUnitConstants();
    }
}

