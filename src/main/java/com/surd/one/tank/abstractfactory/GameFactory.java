package com.surd.one.tank.abstractfactory;

import com.surd.one.Dir;
import com.surd.one.Group;
import com.surd.one.TankFrame;

/**
 * @author admin
 * @date
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y,Dir dir,Group group,TankFrame tf);
}
