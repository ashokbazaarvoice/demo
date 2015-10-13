package com.abc.copy;

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

        a.myMethod(a); // from animal --> overloaded method will be called based on reference type as no overridden method in subclass
        a.myMethod(h); // from animal --> overloaded method will be called based on reference type as no overridden method in subclass
        h.myMethod(a); // from animal --> overloaded method will be called based on reference type but this method is available to subclass, as no overridden method in subclass
        h.myMethod(h); // from horse
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
    // Overloaded Method
    //@Override as its not override it overload
    void myMethod(Horse h){
        System.out.println("from horse " + h.getAnimal());
    }

//    @Override
//    void myMethod(Animal a){
//        System.out.println("from horse -> animal "+a.getAnimal());
//    }

}
class c{

}
class d extends c{

}
interface i{

}
interface j extends i{

}
