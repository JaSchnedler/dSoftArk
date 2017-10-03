package hotciv.framework.interfaces.AgeStrategy.implementations;

import hotciv.framework.interfaces.AgeStrategy.AgeStrategy;

/**
 * Created by smp on 09/11/15.
 */
public class AdvancedAgingStrategy implements AgeStrategy{
    @Override
    public int calculateNewAge(int age) {

        if(isBetween(age, -4000, -100)){
            return age + 100;
        }
        
        else if (age == -100){
            return -1;
        }

        else if(age == -1){
            return 1;
        }

        else if(age == 1){
            return 50;
        }

        else if(isBetween(age, 50, 1750)){
            return age + 50;
        }

        else if(isBetween(age, 1750, 1900)){
            return age + 25;
        }

        else if(isBetween(age, 1900, 1970)){
            return age + 5;
        }

        // After 1970
        else return age + 1;
    }

    /***
     * Evaluates whether or not the provided age is between the lower and higher bounds of an interval.
     * @param age Age of evaluation.
     * @param lower Lower bound of age interval.
     * @param higher Higher bound of age interval.
     * @return
     */
    private boolean isBetween(int age, int lower, int higher){

        return age >= lower && age < higher;
    }
}
