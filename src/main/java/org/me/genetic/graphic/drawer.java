package org.me.genetic.graphic;

import org.me.genetic.vo.Line;

import java.awt.*;

public class drawer {

    public void draw(Line line, Graphics graphics){
        graphics.drawLine(line.x1(),line.y1(),line.x2(),line.y2());
    }
}
