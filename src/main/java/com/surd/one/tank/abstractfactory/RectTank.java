package com.surd.one.tank.abstractfactory;

import com.surd.one.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @Author: gulang
 * @Date: Create in 22:41 2022/7/5
 * @Description: 抽象出坦克类，封装属性和方法
 * @Version: 1.0
 */
public class RectTank extends BaseTank {
    public int x, y;
    public Dir dir = Dir.DOWN;
    private static final int SPEED = 3;
    /**
     * 坦克是否移动
     */
    private boolean moving = true;
    public static final int TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();
    public TankFrame tf;
    private boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();
    public Rectangle rect = new Rectangle();
    FireStrategy fireStrategy;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = TANK_WIDTH;
        rect.height = TANK_HEIGHT;

        try {
            if (group == Group.GOOD) {
                String goodFireStrategy = PropertyMgr.getString("good.fireStrategy");
                Class<?> aClass = Class.forName(goodFireStrategy);
                fireStrategy = (FireStrategy)aClass.getDeclaredConstructor().newInstance();
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
    @Override
    public void paint(Graphics g) {
        //如果坦克没活，则不画
        if (!living) {
            tf.tanks.remove(this);
        }

       Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED :Color.BLUE);
        g.fillRect(x,y,50,50);
        g.setColor(c);

        move();
    }

    private void move() {
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
        if (this.x > TankFrame.GAME_WIDTH - RectTank.TANK_WIDTH - 2) x = TankFrame.GAME_WIDTH - RectTank.TANK_WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.TANK_HEIGHT - 2) y = TankFrame.GAME_HEIGHT - RectTank.TANK_HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //fireStrategy.fire(this);
        //根据坦克图片大小，计算子弹位置
        int bx = this.x + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = this.y + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;


        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            this.tf.gf.createBullet(bx,by,dir,this.group,this.tf);
        }
        if (this.group == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav"));
        }
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


