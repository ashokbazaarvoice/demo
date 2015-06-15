package com.abc.idea.excercise.packageA;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 10/9/14
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class Person {

    private String name;

    String addressLine_1;

    protected String city;

    public String state;

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getAddressLine_1() {
        return addressLine_1;
    }

    void setAddressLine_1(String addressLine_1) {
        this.addressLine_1 = addressLine_1;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }
}
