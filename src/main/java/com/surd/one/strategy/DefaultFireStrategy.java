package com.surd.one.strategy;

import com.surd.one.*;
import com.surd.one.decorator.RectDecorator;
import com.surd.one.decorator.TailDecorator;

/**
 * @author admin
 * @date 默认的子弹策略
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        //根据坦克图片大小，计算子弹位置
        int bx = t.x + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = t.y + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;
        GameModel.getInstance().add(
                new RectDecorator(
                        new TailDecorator(new Bullet(bx,by,t.dir,t.group))));

        if (t.group == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav"));
        }

    }
}
