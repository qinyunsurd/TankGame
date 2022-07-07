package com.surd.one;

import java.awt.*;
import java.util.Random;

/**
 * @Author: gulang
 * @Date: Create in 22:41 2022/7/5
 * @Description: 抽象出坦克类，封装属性和方法
 * @Version: 1.0
 */
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 3;
    /**
     * 坦克是否移动
     */
    private boolean moving = true;
    public static final int TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();
    private TankFrame tf;
    private boolean living = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    public Tank(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    /**
     * 画坦克自己
     * @param g
     */
    public void paint(Graphics g){
        //如果坦克没活，则不画
        if (!living){
            tf.tanks.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving){
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
        //敌机随机发射子弹
        if (this.group == Group.BAD && random.nextInt(100)>95){
            this.fire();
        }
        //当是敌方坦克时，随机改变方向
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //根据坦克图片大小，计算子弹位置
        int bx = this.x + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = this.y + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;
        tf.bullets.add(new Bullet(bx,by,this.dir,this.group,tf));
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}


