package com.surd.one;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public abstract class GameObject {
    public int x;
    public int y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
