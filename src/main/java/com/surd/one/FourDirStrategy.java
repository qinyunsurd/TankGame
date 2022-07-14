package com.surd.one;

/**
 * @author admin
 * @date
 */
public class FourDirStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        //根据坦克图片大小，计算子弹位置
        int bx = t.x + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = t.y + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;


        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx,by,dir,t.group,t.gm);
        }
        if (t.group == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav"));
        }
    }
}
