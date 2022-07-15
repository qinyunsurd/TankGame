package com.surd.one.decorator;

import com.surd.one.GameObject;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public class RectDecorator extends GODecorator{
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(go.x,go.y,this.getWidth(),this.getHeight());
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
