package org.me.genetic.vo;

import org.me.genetic.tools.WolfGenerator;

import java.awt.*;

public class Line {

    private Point start;
    private Point end;
    private double angle = .0f;

    private Line(Point start) {
        new Line(start, .0f);
    }

    private Line(Point start, double angle) {
        this.start=start;
        this.angle = angle;
        end = Point.createPoint();
        recalculateLine();
    }

    public static Line createLine(int x1, int y1, double angle){

        return new Line(Point.createPoint(x1,y1),angle);
    }

    public void recalculateLine(){
        end.x((int)(start.x()+Math.cos(angle)* WolfGenerator.lineLength));
        end.y((int)(start.y()+Math.sin(angle)* WolfGenerator.lineLength));
    }

    public void draw(Graphics graphics){
        ((Graphics2D)graphics).setStroke(new BasicStroke(2));
        graphics.drawLine(start.x(),start.y(),end.x(),end.y());
    }

    public static Line zeroLine(){

        return new Line(Point.createPoint());
    }

    public int x1() {

        return start.x();
    }

    public void x1(int x){
        this.start.x(x);
    }

    public int y1() {

        return start.y();
    }

    public void y1(int y){
        this.start.y(y);
    }

    public int x2() {
        return end.x();
    }

    public void x2(int x){
        this.end.x(x);
    }

    public int y2() {
        return end.y();
    }

    public void y2(int y ){
        this.end.y(y);
    }

    public double angle(){
        return angle;
    }

}
