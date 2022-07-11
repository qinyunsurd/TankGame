package com.surd.one;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gulang
 * @Date: Create in 21:54 2022/7/4
 * @Description:
 * @Version: 1.0
 */
public class TankFrame extends Frame {
    static final int GAME_WIDTH = 800,GAME_HEIGHT =600;
    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    /**
     * 加入敌方坦克
     */
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    Explode e = new Explode(100,100,this);
    public TankFrame() {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("坦克大战");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        setVisible(true);
    }

    /**
     * 用双缓冲解决闪烁问题
     * 首先把该画出来的东西先画到内存中的图片中，图片大小和游戏画面一致
     * 把内存中的图片一次性画到屏幕中（将内容复制到显存中）
     */
    Image offScreenImage;
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" +bullets.size(),10,60);
        g.drawString("敌人的数量：" +tanks.size(),10,80);
        g.drawString("爆炸的数量：" +explodes.size(),10,100);
        g.setColor(c);
        //主战坦克
        myTank.paint(g);
        //敌方坦克
        for (int i = 0; i< tanks.size(); i++){
            tanks.get(i).paint(g);
        }
        //子弹
        for (int i=0;i< bullets.size();i++) {
            bullets.get(i).paint(g);
        }
        for (int i =0; i < explodes.size();i++){
            explodes.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0 ; i < bullets.size(); i++){
            for (int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        //
        //e.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        // 根据方向键状态判断坦克方向
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL =true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
            }
            if (bL) {
                myTank.setDir(Dir.LEFT);
            }
            if (bU) {
                myTank.setDir(Dir.UP);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL =false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
    }

}
