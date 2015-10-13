package com.abc.copy;


import java.io.*;


/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/12/15
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 * The class below uses custom serialisation.
 * Secondly convert a serialized class to another class.
 *
 */
public class SerilizationDemo {

    public static void main(String[] args) throws Exception {
        // The personv2 serialized is different class during serialisation.

//        PersonV2 person = new PersonV2("Firstname", "Lastname", 30);

//        FileOutputStream fos = new FileOutputStream("/Users/ashok.agarwal/dev/github/HbaseRemoteClient/tempdata.ser");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(person);
//        oos.close();

        // de serialisation with different class
        FileInputStream fis = new FileInputStream("/Users/ashok.agarwal/dev/github/HbaseRemoteClient/tempdata.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        PersonV2 ted = (PersonV2) ois.readObject();
        ois.close();


        System.out.println(ted.getFirstName());
//        System.out.println(ted.getSpouse().getFirstName());

        // Clean up the file
//        new File("tempdata.ser").delete();

    }
}

//class PersonV2 implements java.io.Serializable {
//
//    private static final long serialVersionUID = -6036475066468023423L;
//
//    public PersonV2(String fn, String ln, int a) {
//        this.firstName = fn;
//        this.lastName = ln;
//        this.age = a;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public PersonV2 getSpouse() {
//        return spouse;
//    }
//
//    public void setFirstName(String value) {
//        firstName = value;
//    }
//
//    public void setLastName(String value) {
//        lastName = value;
//    }
//
//    public void setAge(int value) {
//        age = value;
//    }
//
//    public void setSpouse(PersonV2 value) {
//        spouse = value;
//    }
//
//    public String toString() {
//        return "[Person: firstName=" + firstName +
//                " lastName=" + lastName +
//                " age=" + age +
//                " spouse=" + spouse.getFirstName() +
//                "]";
//    }
//
//    private void writeObject(ObjectOutputStream out) throws IOException {
//        System.out.println("Called writeObject");
//        out.defaultWriteObject();
//    }
//
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        System.out.println("Called readObject");
//        in.defaultReadObject();
//    }
//
//    private String firstName;
//    private String lastName;
//    private int age;
//    private PersonV2 spouse;
//
//}

enum Gender {
    MALE, FEMALE
}

class PersonV2 implements java.io.Serializable {

    private static final long serialVersionUID = -6036475066468023423L;

    public PersonV2(String fn, String ln, int a)
    {
        this.firstName = fn; this.lastName = ln; this.age = a;
    }

    public PersonV2(String fn, String ln, int a, Gender g) {
        this.firstName = fn;
        this.lastName = ln;
        this.age = a;
        this.gender = g;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public PersonV2 getSpouse() {
        return spouse;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    public void setLastName(String value) {
        lastName = value;
    }

    public void setGender(Gender value) {
        gender = value;
    }

    public void setAge(int value) {
        age = value;
    }

    public void setSpouse(PersonV2 value) {
        spouse = value;
    }

    public String toString() {
        return "[Person: firstName=" + firstName +
                " lastName=" + lastName +
                " gender=" + gender +
                " age=" + age +
                " spouse=" + spouse.getFirstName() +
                "]";
    }

    private String firstName;
    private String lastName;
    private int age;
    private PersonV2 spouse;
    private Gender gender;
}