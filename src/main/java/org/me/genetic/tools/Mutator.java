package org.me.genetic.tools;

import org.me.genetic.vo.Line;
import org.me.genetic.vo.Wolf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mutator {

    public static Stream<Wolf> mutateWolf(Wolf wolf){
        Random random = new Random();
        List<Wolf> newGeneration = new ArrayList<>();

        System.out.println(wolf.getLines()
                .stream()
                .map(line->String.format("%d %d %d %d ",line.x1(),line.y1(),line.x2(),line.y2()))
                .collect(Collectors.joining(" | ")));
        System.out.println("----------------------------------------------------");

        for(int i =0; i< 4; i++){
                newGeneration.addAll(Stream
                        .of(
                                random.nextInt(3),
                                random.nextInt(3)+3,
                                random.nextInt(4)+6)
                        .map(genomeIndex-> {
                            //TODO here is the bug, the reference for the list is cloned, but not changed
                            Wolf newWolf = Wolf.createWolf(wolf.getLines().stream().collect(Collectors.toList()),new Color(
                                    random.nextInt(255),
                                    random.nextInt(255),
                                    random.nextInt(255)));

                            newWolf.getLines()
                                    .set(genomeIndex, Line.createLine(0,0,random.nextFloat()*2*Math.PI));

                            newWolf.color(new Color(
                                    random.nextInt(255),
                                    random.nextInt(255),
                                    random.nextInt(255)));

                            newWolf.recalculateLines();

                            return newWolf;})
                        .collect(Collectors.toList()));
        }

        newGeneration
                .stream()
                .map(x->
                        x
                        .getLines()
                        .stream()
                        .map(line->String.format("%d %d %d %d ",line.x1(),line.y1(),line.x2(),line.y2()))
                        .collect(Collectors.joining(" | ")))
                .forEach(System.out::println);

        return newGeneration.stream();
    }

}
