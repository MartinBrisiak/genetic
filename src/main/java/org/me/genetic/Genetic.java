package org.me.genetic;

import org.me.genetic.tools.Mutator;
import org.me.genetic.tools.WolfGenerator;
import org.me.genetic.vo.Line;
import org.me.genetic.vo.Point;
import org.me.genetic.vo.Sheep;
import org.me.genetic.vo.Wolf;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.awt.Color.*;

public class Genetic extends JPanel{

    public static final int width = 1000;
    public static final int height = 1000;
    public static final int firstGenerationSize = 100;

    private static final int goalX = 100;
    private static final int goalY = 900;

    private List<Line> pastGenerationsLines = new ArrayList<>();
    private Sheep sheep = Sheep.createSheep(Point.createPoint(goalX,goalY));
    private List<Wolf> wolfs = null;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(RED);
        g.fillOval(sheep.x(),sheep.y(),20,20);

        if(wolfs == null){
            wolfs = createFirstGeneration(g);
        }

//        drawPastGenerations(g);
        archiveGeneration(wolfs);

        List<Wolf> winners = new ArrayList<>();
        int winnersCount = firstGenerationSize/10 > 1 ? firstGenerationSize/10 : 1;
        for(int i = 0; i < winnersCount; i++){
            winners.add(wolfs.get(i));
        }

        //TODO spliting into two groups
        //.map(wolf->new AbstractMap.SimpleEntry<Wolf,Wolf>(wolf,wolf));
//        Stream<Wolf> firsHalf = winners.stream().limit(winners.size() / 2);
//        Iterable<Wolf> secondHalfIterable = () -> winners
//                .stream()
//                .collect(Collectors.toCollection(LinkedList::new))
//                .descendingIterator();
//        Stream.StreamSupport.stream(secondHalfIterable.spliterator(),false).limit(winners.size()/2);

        List<Wolf> newGeneration = winners
                .stream()
                .flatMap(Mutator::mutateWolf)
                .peek(wolf -> {
                    wolf.drawLines(g);
                })
                .collect(Collectors.toList());

//        winners
//                .stream()
//                .map(wolf->wolf.getColor())
//                .forEach(color->System.out.println("winner: "+color));

        wolfs = newGeneration;

//        try {
//            Thread.currentThread().sleep(1000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            e.printStackTrace();
//        }

//        repaint();
    }

    private List<Wolf> createFirstGeneration(Graphics g) {
        return WolfGenerator
                    .generateWolfs(firstGenerationSize)
                    .peek(wolf-> wolf.hunt(g,sheep))
                    .sorted(Comparator.comparing(Wolf::getScore))
                    .collect(Collectors.toList());
    }

    private void drawPastGenerations(Graphics g) {
        g.setColor(GRAY);
        pastGenerationsLines
                .stream()
                .forEach(line -> line.draw(g));
    }

    private void archiveGeneration(List<Wolf> wolfs) {
        pastGenerationsLines
                .addAll(
                    wolfs
                        .stream()
                        .flatMap(wolf->
                                wolf
                                    .getLines()
                                    .stream())
                        .collect(Collectors.toList()));
    }

}
