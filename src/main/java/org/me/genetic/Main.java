package org.me.genetic;

import javax.swing.*;

import static java.awt.Color.WHITE;

public class Main {
    public static void main(String... args) {
        System.out.println("Hello World");
//        new Genetic().magic();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        Genetic panel = new Genetic();

        panel.setBackground(WHITE);
        frame.setContentPane(panel);
        frame.setVisible(true);

        while(true){
            panel.repaint();
        }
    }
}
