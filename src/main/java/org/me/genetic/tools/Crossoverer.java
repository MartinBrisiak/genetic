package org.me.genetic.tools;

import org.me.genetic.vo.Line;
import org.me.genetic.vo.Wolf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Crossoverer {

    public static List<Wolf> cross(Wolf first, Wolf second){

        List<Line> pathOfFirstNewWolf = new ArrayList<>();
        pathOfFirstNewWolf.addAll(takeStart(first));
        pathOfFirstNewWolf.addAll(takeMiddle(second));
        pathOfFirstNewWolf.addAll(takeEnd(first));

        List<Line> pathOfSecondNewWolf = new ArrayList<>();
        pathOfSecondNewWolf.addAll(takeStart(second));
        pathOfSecondNewWolf.addAll(takeMiddle(first));
        pathOfSecondNewWolf.addAll(takeEnd(second));

        return Stream.of(
                    Wolf.createWolf(pathOfFirstNewWolf,GeneticUtils.randomColor()),
                    Wolf.createWolf(pathOfSecondNewWolf,GeneticUtils.randomColor()))
                .collect(Collectors.toList());
    }

    private static List<Line> takeMiddle(Wolf wolf){
        List<Line> middle = new ArrayList<>();
        for(int i = 3; i< 6; i++){
            middle.add(wolf.getLines().get(i));
        }

        return middle;
    }

    private static List<Line> takeStart(Wolf wolf){
        List<Line> start = new ArrayList<>();
        for(int i = 0; i< 3; i++){
            start.add(wolf.getLines().get(i));
        }

        return start;
    }

    private static List<Line> takeEnd(Wolf wolf){
        List<Line> end = new ArrayList<>();
        for(int i = 6; i< 10; i++){
            end.add(wolf.getLines().get(i));
        }

        return end;
    }
}
