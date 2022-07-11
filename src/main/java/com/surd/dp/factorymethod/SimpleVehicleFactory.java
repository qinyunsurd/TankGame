package com.surd.dp.factorymethod;

/**
 * @author admin
 * @date 简单工程模式
 * @desc 可扩展性不好
 */
public class SimpleVehicleFactory {

    public Car creatCar(){
        // do something
        return new Car();
    }

    public Broom creatBroom(){

        return new Broom();
    }

    public Plane creatPlane(){

        return new Plane();
    }
}
