package com.surd.one.cor;

import com.surd.one.Bullet;
import com.surd.one.GameObject;
import com.surd.one.Tank;

/**
 * @author admin
 * @date
 */
public class BulletTankCollider implements Collider{

    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            b.collideWith(t);
        } else if (o1 instanceof Tank && o2 instanceof Bullet){
            collider(o2,o1);
        } else {
            return;
        }
    }
}
