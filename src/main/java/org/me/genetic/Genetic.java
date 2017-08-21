package org.me.genetic;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import org.me.genetic.tools.Crossoverer;
import org.me.genetic.tools.GeneticUtils;
import org.me.genetic.tools.Mutator;
import org.me.genetic.tools.WolfGenerator;
import org.me.genetic.vo.Line;
import org.me.genetic.vo.Point;
import org.me.genetic.vo.Sheep;
import org.me.genetic.vo.Wolf;

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
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
    public static final int winnersCount = firstGenerationSize/10 > 1 ? firstGenerationSize/10 : 1;

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

        System.out.println("scores: ");
        List<Wolf> winners = wolfs
                .stream()
                .peek(wolf-> wolf.hunt(g,sheep))
                .sorted(Comparator.comparing(Wolf::getScore))
                .peek(wolf->System.out.print(wolf.getScore()+" "))
                .limit(winnersCount)
//                .peek(wolf-> System.out.print(wolf.getScore()+ ", "))
                .collect(Collectors.toList());
//                .subList(0,winnersCount);

        int partitionSize = IntMath.divide(winners.size(), 2, RoundingMode.UP);
        List<List<Wolf>> partitions = Lists.partition(winners, partitionSize);

//        System.out.println();

        List<Map.Entry<Wolf,Wolf>> groupedWinners = new ArrayList<>();
        for(int i=0; i< partitions.get(0).size(); i++){
            groupedWinners.add(
                new AbstractMap.SimpleEntry<>(
                    partitions.get(0).get(i),
                    partitions.size()>1? partitions.get(1).get(i) : partitions.get(0).get(i)
                ));
        }

        //TODO this flatMap is acting funny
        List<Wolf> newGeneration = groupedWinners
                .stream()
                .flatMap(pair -> Crossoverer.cross(pair.getKey(),pair.getValue()).stream())
                .flatMap(Mutator::mutateWolf)
                .collect(Collectors.toList());

        wolfs = newGeneration;
        wolfs.addAll(winners);

        wolfs.stream().forEach(wolf->{
            wolf.drawLines(g);
        });

//        System.out.println("new generation Size: "+wolfs.size());
        System.out.print("Winners: ");

//        printing winners
        winners
                .stream()
//                .map(wolf->wolf.getColor())
                .forEach(wolf->System.out.print(String.format("F:%d R:%d, G:%d, B:%d | ",
                                                                        wolf.getScore(),
                                                                        wolf.getColor().getRed(),
                                                                        wolf.getColor().getGreen(),
                                                                        wolf.getColor().getBlue())));

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

    }

    private List<Wolf> createFirstGeneration(Graphics g) {
        return WolfGenerator
                    .generateWolfs(firstGenerationSize)
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
