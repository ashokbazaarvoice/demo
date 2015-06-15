package com.bazaarvoice.idea;

/**
 * Created by ashok.agarwal on 3/11/14.
 */
public class MyConcreteClass extends MyAbstract {

    public MyConcreteClass(){
        super();
        System.out.println("MyConcreteClass constructor called");
    }

   // @Override
    public void mymethod() {
        super.whoami();
        System.out.println("mymthod of MyConcreteClass called");
    }
}
