package com.surd.dp.factorymethod;

/**
 * @author admin
 * @date
 */
public class CarFactory {

    public Moveable create(){
        // do something
        return new Car();
    }
}
