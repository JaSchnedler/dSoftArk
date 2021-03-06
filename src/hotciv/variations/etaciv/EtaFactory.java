package hotciv.variations.etaciv;

import hotciv.framework.interfaces.UnitConstantsStrategy.UnitConstantsStrategy;
import hotciv.framework.interfaces.UnitConstantsStrategy.implementations.DefaultUnitConstants;
import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;
import hotciv.framework.interfaces.AgeStrategy.implementations.IncrementalAgingStrategy;
import hotciv.framework.interfaces.AttackCountStrategy.implementations.NoAttackCountStrategy;
import hotciv.framework.interfaces.CombatStrategy.CombatStrategy;
import hotciv.framework.interfaces.CombatStrategy.implementations.AttackerWinsCombatStrategy;
import hotciv.framework.interfaces.GameFactory;
import hotciv.framework.interfaces.GameState.GameState;
import hotciv.framework.interfaces.GameState.implementations.GameStateImpl;
import hotciv.framework.interfaces.StateChangeStrategy.StateChangeStrategy;
import hotciv.framework.interfaces.StateChangeStrategy.implementations.NoStateChangeStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.UnitActionStrategy;
import hotciv.framework.interfaces.UnitActionStrategy.implementations.NoUnitActionStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.UnitStateStrategy;
import hotciv.framework.interfaces.UnitStateStrategy.implementations.DefaultUnitStateStrategy;
import hotciv.framework.interfaces.WinnerStrategy.implementations.SimpleWinnerStrategy;
import hotciv.framework.interfaces.WorldStrategy.WorldStrategy;
import hotciv.framework.interfaces.WorldStrategy.implementations.FixedWorldStrategyTestStub;
import hotciv.framework.interfaces.ResourceStrategy.implementations.AdvancedResourceStrategy;
import hotciv.framework.interfaces.ResourceStrategy.ResourceStrategy;

/**
 * Created by Jacob on 30-11-2015.
 */
public class EtaFactory implements GameFactory{
    @Override
    public WorldStrategy createWorldStrategy() {
        return new FixedWorldStrategyTestStub();
    }

    @Override
    public AgeStrategy createAgeStrategy() {
        return new IncrementalAgingStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new NoUnitActionStrategy();
    }

    @Override
    public CombatStrategy createCombatStrategy() {
        return new AttackerWinsCombatStrategy();
    }

    @Override
    public GameState createDefaultGameState() {
        return new GameStateImpl(new SimpleWinnerStrategy(), new NoAttackCountStrategy());
    }

    @Override
    public GameState createAlternateGameState() {
        return createDefaultGameState();
    }

    @Override
    public StateChangeStrategy createStateChangeStrategy() {
        return new NoStateChangeStrategy();
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
