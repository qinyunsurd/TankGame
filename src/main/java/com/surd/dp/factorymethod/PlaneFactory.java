package com.surd.dp.factorymethod;

/**
 * @author admin
 * @date
 */
public class PlaneFactory {

    public Moveable create(){
        // do something
        return new Plane();
    }
}
