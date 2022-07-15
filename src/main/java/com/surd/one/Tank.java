package com.surd.one;

import com.surd.one.strategy.FireStrategy;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @Author: gulang
 * @Date: Create in 22:41 2022/7/5
 * @Description: 抽象出坦克类，封装属性和方法
 * @Version: 1.0
 */
public class Tank extends GameObject{
    public int oldX, oldY;
    public Dir dir = Dir.DOWN;
    private static final int SPEED = 3;
    /**
     * 坦克是否移动
     */
    private boolean moving = true;
    public static final int TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();
    private boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rect = new Rectangle();
    FireStrategy fireStrategy;

    public GameModel gm;
    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = TANK_WIDTH;
        rect.height = TANK_HEIGHT;
        this.gm = gm;
        try {
            if (group == Group.GOOD) {
                String goodFireStrategy = PropertyMgr.getString("good.fireStrategy");
                Class<?> aClass = Class.forName(goodFireStrategy);
                fireStrategy = (FireStrategy)aClass.getDeclaredConstructor().newInstance();
                //fireStrategy = (FireStrategy) aClass.newInstance();
            } else {
                String badFireStrategy = PropertyMgr.getString("bad.fireStrategy");
                Class<?> aClass = Class.forName(badFireStrategy);
                fireStrategy = (FireStrategy) aClass.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 画坦克自己
     *
     * @param g
     */
    public void paint(Graphics g) {
        //如果坦克没活，则不画
        if (!living) {
            gm.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        this.oldX = x;
        this.oldY = y;
        if (!moving) {
            return;
        }
        switch (dir) {
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
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        //当是敌方坦克时，随机改变方向
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        //边界检测
        boundsCheck();
        //更新rectangle
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 2;
    }

    public void  goBack(){
        this.x = this.oldX;
        this.y = this.oldY;
    }
    public void stop(){
        moving = false;
    }
    public Rectangle getRect() {
        return rect;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fireStrategy.fire(this);
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


