package hotciv.framework.interfaces.AgeStrategy;

/**
 * Created by smp on 09/11/15.
 */
public interface AgeStrategy {
    /***
     * Calculates the age of the game.
     * Should be called after each end-round.
     */
    public int calculateNewAge(int currentAge);
}
