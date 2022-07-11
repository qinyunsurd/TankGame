package com.surd.dp.abstractfactory;

/**
 * @author admin
 * @date
 */
public abstract class AbstractFactory {
    abstract Food createFood();
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();
}
