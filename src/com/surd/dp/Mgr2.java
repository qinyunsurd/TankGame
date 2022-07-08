package com.surd.dp;


/**
 * @author admin
 * @date
 */
public enum Mgr2 {
    INSTANCE;

    public void m(){
        System.out.println("test");
    }



    public static void main(String[] args) {
        Mgr2 m = Mgr2.INSTANCE;
        m.m();

    }
}
