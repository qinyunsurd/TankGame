package com.surd.one;

import java.awt.*;

/**
 * @Author: gulang
 * @Date: Create in 22:54 2022/7/5
 * @Description:
 * @Version: 1.0
 */
public class Bullet extends GameObject{
    private Dir dir =Dir.DOWN;
    private static final int SPEED = 10;
    public static final int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    public static final int BULLET_HEIGHT = ResourceMgr.bulletL.getHeight();
    private boolean living = true;
    public Group group = Group.BAD;
    private Rectangle rect = new Rectangle();
    public Bullet(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = BULLET_WIDTH;
        rect.height = BULLET_HEIGHT;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g){
        if (!living){
            GameModel.getInstance().remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }

        move();
    }

    @Override
    public int getWidth() {
        return BULLET_WIDTH;
    }

    @Override
    public int getHeight() {
        return BULLET_HEIGHT;
    }

    private void move() {
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        //更新rect值
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void die() {
        this.living = false;
    }

}
