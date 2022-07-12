package com.surd.one.tank.abstractfactory;

import com.surd.one.Tank;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank) ;
}
