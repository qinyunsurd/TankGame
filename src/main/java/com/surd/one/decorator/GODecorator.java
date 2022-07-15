package com.surd.one.decorator;

import com.surd.one.GameObject;

import java.awt.*;

/**
 * @author admin
 * @date 装饰者模式
 */
public abstract class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }


}
