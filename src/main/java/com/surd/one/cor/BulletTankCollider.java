package com.surd.one.cor;

import com.surd.one.*;

/**
 * @author admin
 * @date
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            //b.collideWith(t);
            if (this.collideWith(b, t)) {
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collider(o2, o1);
        }
        return true;
    }

    public boolean collideWith(Bullet b, Tank t) {
        if (b.group == t.getGroup()) {
            return false;
        }
        //todo: 用一个rect来记录子弹的位置，new太多垃圾回收会吃力
        //Rectangle rect = new Rectangle(this.x,this.y,BULLET_WIDTH,BULLET_HEIGHT);
        //Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
        //判断是否相交，如果相交，则坦克和子弹都死亡
        if (b.getRect().intersects(t.getRect())) {
            t.die();
            b.die();
            //根据坦克位置大小，计算爆炸位置
            int ex = t.getX() + Tank.TANK_WIDTH / 2 - Explode.EXPLODE_WIDTH / 2;
            int ey = t.getY() + Tank.TANK_HEIGHT / 2 - Explode.EXPLODE_HEIGHT / 2;
            new Explode(ex, ey);
            return true;
        }
        return false;
    }
}
