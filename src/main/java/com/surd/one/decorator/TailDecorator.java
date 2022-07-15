package com.surd.one.decorator;

import com.surd.one.GameObject;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public class TailDecorator extends GODecorator{
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.drawLine(go.x,go.y,go.x+getWidth(),go.y+getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
