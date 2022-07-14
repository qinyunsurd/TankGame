package com.surd.one;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date
 */
public class GameModel {
    static final int GAME_WIDTH = 800,GAME_HEIGHT =600;
    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    /**
     * 加入敌方坦克
     */
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            tanks.add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" +bullets.size(),10,60);
        g.drawString("敌人的数量：" +tanks.size(),10,80);
        g.drawString("爆炸的数量：" +explodes.size(),10,100);
        g.setColor(c);
        //主战坦克
        myTank.paint(g);
        //敌方坦克
        for (int i = 0; i< tanks.size(); i++){
            tanks.get(i).paint(g);
        }
        //子弹
        for (int i=0;i< bullets.size();i++) {
            bullets.get(i).paint(g);
        }
        for (int i =0; i < explodes.size();i++){
            explodes.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0 ; i < bullets.size(); i++){
            for (int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    /**
     * 主战坦克
     * @return
     */
    public Tank getMainTank() {
        return myTank;
    }
}
