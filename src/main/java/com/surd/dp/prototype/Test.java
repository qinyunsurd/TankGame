package com.surd.dp.prototype;

/**
 * @author admin
 * @date
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1= new Person();
        Person p2 = (Person) p1.clone();
        System.out.println(p1 == p2);
        System.out.println(p1.loc.street);
        p1.loc.street="sh";
        System.out.println(p1.loc == p2.loc);
        System.out.println(p2.loc.street);
        System.out.println(p1.loc.street);
        System.out.println(p1.loc.street == p2.loc.street);
    }

}

class Person implements  Cloneable{
    int age =8;
    int score=100;
    Location loc = new Location("bj","101");

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.loc = (Location) loc.clone();
        return p;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}

class Location implements Cloneable{
    String street="bj";
    String roomNo ="101";

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
