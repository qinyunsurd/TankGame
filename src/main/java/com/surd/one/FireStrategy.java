package com.surd.one;

import com.surd.one.tank.abstractfactory.BaseTank;

/**
 * @author admin
 * @date 子弹策略接口
 */
public interface FireStrategy {
    void fire(Tank t);
}
