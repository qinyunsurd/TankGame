package com.surd.dp.abstractfactory;

/**
 * @author admin
 * @date
 */
public class ModernFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new Ak47();
    }
}
