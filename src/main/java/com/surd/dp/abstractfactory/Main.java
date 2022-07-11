package com.surd.dp.abstractfactory;

/**
 * @author admin
 * @date
 */
public class Main {

    public static void main(String[] args) {
        AbstractFactory factory = new MagicFactory();

        Vehicle vehicle = factory.createVehicle();
        vehicle.go();
        Weapon weapon = factory.createWeapon();
        weapon.shoot();
        Food food = factory.createFood();
        food.printName();

    }
}
