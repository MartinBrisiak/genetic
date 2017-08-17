package org.me.genetic.tools;

import org.me.genetic.Genetic;
import org.me.genetic.tools.GeneticUtils;
import org.me.genetic.vo.Line;
import org.me.genetic.vo.Wolf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class WolfGenerator {

    public static final int lineLength = 50;

    private static final int linesCont = 10;
    private static final int colorMaximum = 255;
    private static final int startingX = 0;
    private static final int startingY = 0;

    public static Stream<Wolf> generateWolfs(int count) {
        List<Wolf> wolfs = new ArrayList<>();
        for(int i=0; i< count; i++){
            wolfs.add(generateWolf());
        }

        return wolfs.stream();
    }

    public static Wolf generateWolf(){
        Random random = new Random();

        int widthModifier = random.nextBoolean() ? 1 : -1;
        int heightModifier = random.nextBoolean() ? 1 : -1;
        int firstInitialValue = random.nextInt(lineLength) * widthModifier + Genetic.width/2;

        Line firstLine = Line.createLine(
                            startingX + Genetic.width/2,
                            startingY + Genetic.height/2,
                            firstInitialValue,
                            GeneticUtils.calculateSecondSide(firstInitialValue - Genetic.width/2,lineLength) * heightModifier +Genetic.height/2);

        List<Line> lines = new ArrayList<>();
        lines.add(firstLine);

        for(int i=0; i < linesCont-1; i++){
            widthModifier = random.nextBoolean() ? 1 : -1;
            heightModifier = random.nextBoolean() ? 1 : -1;

            Line lastLine = lines
                                .stream()
                                .reduce((a,b)->b)
                                .orElseGet(()->Line.createLine(0,0,0,0));

            int firstValue = random.nextInt(lineLength);
            lines.add(Line.createLine(
                            lastLine.x2(),
                            lastLine.y2(),
                            lastLine.x2() + firstValue * widthModifier,
                            lastLine.y2() + GeneticUtils.calculateSecondSide(firstValue,lineLength)*heightModifier));
        }

        debugCount(lines.stream());

        return Wolf.createWolf(lines, new Color(
                                                    random.nextInt(colorMaximum),
                                                    random.nextInt(colorMaximum),
                                                    random.nextInt(colorMaximum)));
    }

    private static void debugCount(Stream<Line> lines){
        lines.forEach(line->{
            int x = line.x2() - line.x1();
            int y = line.y2() - line.y1();
            System.out.println(GeneticUtils.calculateHypotenuse(x,y));
        });

    }
}
