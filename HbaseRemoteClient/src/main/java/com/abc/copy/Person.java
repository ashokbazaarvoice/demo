package com.abc.copy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/17/14
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String print(String name) {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Person)) return false;
//
//        Person person = (Person) o;
//
//        if (!name.equals(person.name)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return name.hashCode();
//    }
}
