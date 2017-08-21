package org.me.genetic.tools;

import org.me.genetic.Genetic;
import org.me.genetic.vo.Line;
import org.me.genetic.vo.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Mutator {

    public static Stream<Wolf> mutateWolf(Wolf wolf){
        Random random = new Random();
        List<Wolf> newGeneration = new ArrayList<>();

        for(int i = 0; i< Genetic.firstGenerationSize/Genetic.winnersCount; i++) {

            int startGenomeIndex = random.nextInt(3);
            int middleGenomeIndex = random.nextInt(3) + 3;
            int endGenomeIndex = random.nextInt(6) + 4;

            Wolf newWolf = Wolf.createWolf(
                    new ArrayList<>(wolf.getLines()), GeneticUtils.randomColor());

            newWolf.getLines()
                    .set(startGenomeIndex, Line.createLine(0, 0, random.nextFloat() * 2 * Math.PI));
            newWolf.getLines()
                    .set(middleGenomeIndex, Line.createLine(0, 0, random.nextFloat() * 2 * Math.PI));
            newWolf.getLines()
                    .set(endGenomeIndex, Line.createLine(0, 0, random.nextFloat() * 2 * Math.PI));

            newWolf.recalculateLines();

            newGeneration.add(newWolf);
        }

        return newGeneration.stream();
    }

}
