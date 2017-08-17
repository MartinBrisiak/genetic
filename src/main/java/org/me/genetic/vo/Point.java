package org.me.genetic.vo;

public class Point {

    private int x;
    private int y;

    private Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    public static Point createPoint(){
       return new Point(0,0);
    }

    public static Point createPoint(int x, int y){
        return new Point(x,y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
