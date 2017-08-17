package org.me.genetic;

import org.me.genetic.tools.Mutator;
import org.me.genetic.tools.WolfGenerator;
import org.me.genetic.vo.Point;
import org.me.genetic.vo.Sheep;
import org.me.genetic.vo.Wolf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;

import static java.awt.Color.*;

public class Genetic extends JPanel{

    public static final int goalX = 100;
    public static final int goalY = 900;
    public static final int width = 1000;
    public static final int height = 1000;

    @Override
    protected void paintComponent(Graphics g) {
        Sheep sheep = Sheep.createSheep(Point.createPoint(goalX,goalY));
        super.paintComponent(g);
        g.setColor(RED);
        g.fillOval(sheep.x(),sheep.y(),20,20);

        List<Wolf> wolfs = WolfGenerator
                .generateWolfs(100)
                .peek(wolf-> wolf.hunt(g,sheep))
                .sorted(Comparator.comparing(Wolf::getScore))
                .collect(Collectors.toList());

        List<Wolf> winners = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            winners.add(wolfs.get(i));
        }

        winners
                .stream()
                .flatMap(Mutator::mutateWolf)
                .map(Wolf::getColor)
                .forEach(System.out::println);

        winners
                .stream()
                .map(wolf->wolf.getColor())
                .forEach(color->System.out.println("winner: "+color));
//        g.setColor(GRAY);
//        wolfs
//            .stream()
//            .flatMap(wolf -> wolf.getLines().stream())
//            .forEach(line -> line.draw(g));

    }

    public void magic() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        Genetic panel = new Genetic();
        panel.setBackground(WHITE);
        frame.setContentPane(panel);
        frame.setVisible(true);

        frame.invalidate();
    }

}
