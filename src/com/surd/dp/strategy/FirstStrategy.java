package com.surd.dp.strategy;

import java.util.Arrays;

/**
 * @author admin
 * @date  策略模式1
 */
public class FirstStrategy {

    public static void main(String[] args) {

        Cat[] c = {new Cat(3,3),new Cat(5,5),new Cat(5,5),new Cat(1,1)};
        //Dog[] d = {new Dog(1),new Dog(5),new Dog(3)};
        Sorter<Cat> dogSorter = new Sorter<>();
        dogSorter.sort(c, new CatHeightComparator());
        System.out.println(Arrays.toString(c));

        //int[] a = {9,3,2,5,7,1,3};
        //Sorter.sort(a);
        //System.out.println(Arrays.toString(a));
    }
}
