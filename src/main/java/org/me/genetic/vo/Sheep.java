package org.me.genetic.vo;

public class Sheep implements Animal {

    private Point position;

    private Sheep(Point position){
        this.position=position;
    }

    public static Sheep createSheep(){
        return new Sheep(Point.createPoint());
    }

    public static Sheep createSheep(Point position){
        return new Sheep(position);
    }

    public static Sheep createSheep(int x, int y){
        return new Sheep(Point.createPoint(x, y));
    }

    @Override
    public Point position() {
        return position;
    }

    @Override
    public int x() {
        return position.x();
    }

    @Override
    public int y() {
        return position.y();
    }
}
