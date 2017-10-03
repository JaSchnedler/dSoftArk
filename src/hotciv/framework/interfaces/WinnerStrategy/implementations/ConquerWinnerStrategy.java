package hotciv.framework.interfaces.WinnerStrategy.implementations;

import hotciv.framework.interfaces.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.interfaces.WinnerStrategy.WinnerStrategy;
import hotciv.standard.GameImpl;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by jrt on 16/11/15.
 */
public class ConquerWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(GameImpl game) {

        HashMap<Position, City> cities = game.getCities();
        Player winner = null;

        Iterator<City> iterator = cities.values().iterator();

        while(iterator.hasNext()){

            Player owner = iterator.next().getOwner();

            // First Iteration
            if(winner == null) {
                winner = owner;
            }
            // Cities are split --> no winner
            else if(winner != owner){
                return null;
            }
        }

        return winner;
    }
}
