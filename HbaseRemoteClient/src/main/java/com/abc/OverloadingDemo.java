package com.abc;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 12/29/14
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class OverloadingDemo {

    public static void main(String[] str){
        Horse h = new Horse();
        h.setAnimal("White Horse");
        Animal a = h;

        a.myMethod(a);
        a.myMethod(h);
        h.myMethod(a);
        h.myMethod(h);
    }
}
class Animal{
    private String animal;

    String getAnimal() {
        return animal;
    }

    void setAnimal(String animal) {
        this.animal = animal;
    }

    void myMethod(Animal a){
        System.out.println("from animal "+a.getAnimal());
    }
}
class Horse extends Animal{
    void myMethod(Horse h){
        System.out.println("from horse " + h.getAnimal());
    }
}
class c{

}
class d extends c{

}
interface i{

}
interface j extends i{

}
