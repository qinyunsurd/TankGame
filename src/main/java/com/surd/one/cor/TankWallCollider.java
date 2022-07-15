package com.surd.one.cor;

import com.surd.one.GameObject;
import com.surd.one.Tank;
import com.surd.one.Wall;

/**
 * @author admin
 * @date
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;

            if (t.getRect().intersects(w.getRect())) {
                t.goBack();
            }
            return false;
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collider(o2, o1);
        }
        return true;
    }

}
