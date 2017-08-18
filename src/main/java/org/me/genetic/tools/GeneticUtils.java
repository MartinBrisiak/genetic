package org.me.genetic.tools;

import java.awt.*;
import java.util.Random;

public class GeneticUtils {

    public static int calculateHypotenuse(int firstSide, int secondSide){
        return (int)Math.sqrt(Math.pow(firstSide, 2) + Math.pow(secondSide, 2));
    }

    public static int calculateSecondSide(int firstSide, int hypotenuse){
        return (int)Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(firstSide, 2));
    }

    public static Color randomColor(){
        Random random = new Random();
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
}
