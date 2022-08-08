package com.surd.one;

import java.awt.*;
import java.io.Serializable;

/**
 * @author admin
 * @date
 */
public abstract class GameObject implements Serializable {
    public int x;
    public int y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
