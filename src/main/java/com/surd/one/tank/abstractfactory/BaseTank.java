package com.surd.one.tank.abstractfactory;

import com.surd.one.Group;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public abstract class BaseTank {
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();
    public abstract void paint(Graphics g);

    public  Group getGroup() {
        return this.group;
    }

    public abstract void die() ;

    public abstract int getX() ;
    public abstract int getY() ;
}
