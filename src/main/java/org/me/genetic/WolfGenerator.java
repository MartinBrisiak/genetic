package org.me.genetic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class WolfGenerator {

    private static final int linesCont = 10;
    private static final int lineLength = 100;
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

        int firstInitialValue = random.nextInt(lineLength);

        Line firstLine = Line.createLine(
                            startingX,
                            startingY,
                            firstInitialValue,
                            GeneticUtils.calculateSecondSide(firstInitialValue,lineLength));

        List<Line> lines = new ArrayList<>();
        lines.add(firstLine);

        for(int i=0; i < linesCont-1; i++){
            Line lastLine = lines
                                .stream()
                                .reduce((a,b)->b)
                                .orElseGet(()->Line.createLine(0,0,0,0));

            int firstValue = random.nextInt(lineLength);
            lines.add(Line.createLine(
                            lastLine.getX2(),
                            lastLine.getY2(),
                            lastLine.getX2() + firstValue,
                            lastLine.getY2() + GeneticUtils.calculateSecondSide(firstValue,lineLength)));
        }

        debugCount(lines.stream());

        return Wolf.createWolf(lines, new Color(
                                                    random.nextInt(colorMaximum),
                                                    random.nextInt(colorMaximum),
                                                    random.nextInt(colorMaximum)));
    }

    private static void debugCount(Stream<Line> lines){
        lines.forEach(line->{
            int x = line.getX2() - line.getX1();
            int y = line.getY2() - line.getY1();
            System.out.println(GeneticUtils.calculateHypotenuse(x,y));
        });

    }
}
