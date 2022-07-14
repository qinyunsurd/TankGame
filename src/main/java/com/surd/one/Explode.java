package com.surd.one;

import java.awt.*;

/**
 * @Author: gulang
 * @Date: Create in 22:54 2022/7/5
 * @Description: 爆炸实体类
 * @Version: 1.0
 */
public class Explode extends GameObject{
    private int x,y;
    public static final int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private boolean living = true;
    private GameModel gm;
    private  int step = 0;
    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length){
            gm.remove(this);
        }
    }


}
