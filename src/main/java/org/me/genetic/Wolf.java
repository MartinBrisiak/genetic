package org.me.genetic;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;

public class Wolf {

    private List<Line> lines;
    private Color color;
    private int score = 1000;

    private Wolf(List<Line> lines, Color color){
        this.color=color;
        this.lines=lines;
    }

    public static Wolf createWolf(List<Line> lines){
        return new Wolf(lines,BLUE);
    }

    public static Wolf createWolf(List<Line> lines,Color color){
        return new Wolf(lines,color);
    }

    public static Wolf zeroWolf(){
        return Wolf.createWolf(Collections.singletonList(Line.zeroLine()),BLACK);
    }

    public void hunt(Graphics g){
        drawLines(g);
        calculateScore();
    }

    private void calculateScore() {
        Line lastLine = lines.stream().reduce((a,b)->b).orElseGet(()->Line.zeroLine());
        this.score = GeneticUtils.calculateHypotenuse(lastLine.getX2()-Genetic.goalX,lastLine.getY2()-Genetic.goalY);
    }

    public void drawLines(Graphics g){
        g.setColor(color);
        lines
            .forEach(line -> line.draw(g));
    }

    public List<Line> getLines(){
        return lines;
    }

    public Color getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }
}
