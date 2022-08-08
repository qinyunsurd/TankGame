package com.surd.dp.templatemethod;

/**
 * @author admin
 * @date 模板方法，其实就是钩子函数
 */
public class Main {
    public static void main(String[] args) {
        F f = new C1();
        f.m();
    }
}

abstract class F{
    void m(){
        op1();
        op2();
    }

    abstract void op1();
    abstract void op2();
}

class C1 extends F{

    @Override
    void op1() {
        System.out.println("op1");
    }

    @Override
    void op2() {
        System.out.println("op2");
    }
}
