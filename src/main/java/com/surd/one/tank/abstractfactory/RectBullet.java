package com.surd.one.tank.abstractfactory;

import com.surd.one.*;

import java.awt.*;

/**
 * @Author: gulang
 * @Date: Create in 22:54 2022/7/5
 * @Description:
 * @Version: 1.0
 */
public class RectBullet extends BaseBullet {
    private int x,y;
    private Dir dir =Dir.DOWN;
    private static final int SPEED = 10;
    public static final int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    public static final int BULLET_HEIGHT = ResourceMgr.bulletL.getHeight();
    private boolean living = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    private Rectangle rect = new Rectangle();
    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = BULLET_WIDTH;
        rect.height = BULLET_HEIGHT;
        tf.bullets.add(this);
    }

    @Override
    public void paint(Graphics g){
        if (!living){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,20,20);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        //更新rect值
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()){
            return;
        }
        //todo: 用一个rect来记录子弹的位置，new太多垃圾回收会吃力，所以rect放在自己的属性中，随时更新，
        //Rectangle rect = new Rectangle(this.x,this.y,BULLET_WIDTH,BULLET_HEIGHT);
        //Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
        //判断是否相交，如果相交，则坦克和子弹都死亡
        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            //根据坦克位置大小，计算爆炸位置
            int ex = tank.getX() + Tank.TANK_WIDTH/2 - Explode.EXPLODE_WIDTH/2;
            int ey = tank.getY() + Tank.TANK_HEIGHT/2 - Explode.EXPLODE_HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(ex,ey,tf));
        }
    }

    /**
     * 消失
     */
    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
