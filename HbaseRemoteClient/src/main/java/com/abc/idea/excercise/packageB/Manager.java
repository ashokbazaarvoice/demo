package com.abc.idea.excercise.packageB;


import com.abc.idea.excercise.packageA.Person;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/9/14
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Manager extends Person {

    public void getMyInfo(){
        System.out.println(getName()); // name not directly accessible as private
        //System.out.println(addressLine_1); // default/friendly so not accessible out of package
        System.out.println(city); // accessible as child due to protected
    }

}
