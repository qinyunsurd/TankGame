package com.surd.dp;

/**
 * @author admin
 * @date
 */
public class Mgr1 {
    private Mgr1() {
    }

    private static class Mgr{
        private final static Mgr1 MGR_1 = new Mgr1();
    }

    public static Mgr1 getInstance(){
        return Mgr.MGR_1;
    }

    public void m(){}

}