package com.surd.one.tank.abstractfactory;

import com.surd.one.Dir;
import com.surd.one.Group;
import com.surd.one.TankFrame;

/**
 * @author admin
 * @date 方形工厂
 */
public class RectFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir,Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,group,tf);
    }
}
