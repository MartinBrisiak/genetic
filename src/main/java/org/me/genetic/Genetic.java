package org.me.genetic;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

import static java.awt.Color.RED;
import static java.awt.Color.WHITE;

public class Genetic extends JPanel{

    public static final int goalX = 100;
    public static final int goalY = 900;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(RED);
        g.fillOval(goalX,goalY,20,20);
        System.out.println("winner color "+ WolfGenerator
                .generateWolfs(10)
                .peek(wolf-> wolf.hunt(g))
                .sorted(Comparator.comparing(Wolf::getScore))
                .findFirst()
                .orElseGet(()->Wolf.zeroWolf())
                .getColor());

    }

    public void magic() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        Genetic panel = new Genetic();
        panel.setBackground(WHITE);
        frame.setContentPane(panel);
        frame.setVisible(true);

        frame.invalidate();
    }

}