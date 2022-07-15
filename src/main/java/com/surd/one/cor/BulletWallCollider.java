package com.surd.one.cor;

import com.surd.one.*;

/**
 * @author admin
 * @date
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;

            if (b.getRect().intersects(w.getRect())) {
                b.die();
            }
            return false;
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collider(o2, o1);
        }
        return true;
    }

}
