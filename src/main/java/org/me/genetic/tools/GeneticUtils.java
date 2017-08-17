package org.me.genetic.tools;

public class GeneticUtils {

    public static int calculateHypotenuse(int firstSide, int secondSide){
        return (int)Math.sqrt(Math.pow(firstSide, 2) + Math.pow(secondSide, 2));
    }

    public static int calculateSecondSide(int firstSide, int hypotenuse){
        return (int)Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(firstSide, 2));
    }
}
