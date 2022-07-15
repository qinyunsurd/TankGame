package com.surd.one.cor;

import com.surd.one.GameObject;
import com.surd.one.Tank;

/**
 * @author admin
 * @date
 */
public class TankTankCollider implements Collider{

    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())){
                t1.goBack();
                t2.goBack();
                //t1.stop();
                //t2.stop();
            }
        }
    }
}
