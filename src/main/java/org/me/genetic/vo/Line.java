package org.me.genetic.vo;

import java.awt.*;

public class Line {

    private final Point start;
    private final Point end;

    private Line(Point start, Point end) {
        this.start=start;
        this.end=end;
    }

    public static Line createLine(int x1, int y1, int x2, int y2){

        return new Line(Point.createPoint(x1,y1),Point.createPoint(x2,y2));
    }

    public static Line zeroLine(){
        return new Line(Point.createPoint(),Point.createPoint());
    }

    public int x1() {

        return start.x();
    }

    public int y1() {

        return start.y();
    }

    public int x2() {
        return end.x();
    }

    public int y2() {
        return end.y();

    }

    public void draw(Graphics graphics){
        ((Graphics2D)graphics).setStroke(new BasicStroke(3));
        graphics.drawLine(start.x(),start.y(),end.x(),end.y());
    }
}
