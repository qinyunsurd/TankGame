package com.surd.dp.factorymethod;

/**
 * @author admin
 * @date
 */
public  class Main {
    public static void main(String[] args) {
        Moveable car = new PlaneFactory().create();
        car.go();
    }
}
