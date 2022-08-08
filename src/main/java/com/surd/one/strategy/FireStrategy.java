package com.surd.one.strategy;

import com.surd.one.Tank;

import java.io.Serializable;

/**
 * @author admin
 * @date 子弹策略接口
 */
public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
