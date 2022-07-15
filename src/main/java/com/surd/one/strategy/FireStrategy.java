package com.surd.one.strategy;

import com.surd.one.Tank;

/**
 * @author admin
 * @date 子弹策略接口
 */
public interface FireStrategy {
    void fire(Tank t);
}