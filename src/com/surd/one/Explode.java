package com.surd.one;

import java.awt.*;

/**
 * @Author: gulang
 * @Date: Create in 22:54 2022/7/5
 * @Description:
 * @Version: 1.0
 */
public class Explode {
    private int x,y;
    public static final int BULLET_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int BULLET_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    private TankFrame tf;
    private  int step = 0;
    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length){
            step = 0;
        }
    }


}
