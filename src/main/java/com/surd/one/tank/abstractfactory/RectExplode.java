package com.surd.one.tank.abstractfactory;

import com.surd.one.Audio;
import com.surd.one.ResourceMgr;
import com.surd.one.TankFrame;

import java.awt.*;

/**
 * @author admin
 * @date
 */
public class RectExplode extends BaseExplode{
    private int x,y;
    public static final int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    private TankFrame tf;
    private  int step = 0;
    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
    @Override
    public void paint(Graphics g){
        //g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if (step >= 15){
            tf.explodes.remove(this);
        }
        g.setColor(c);
    }
}
