package com.bazaarvoice.idea;

import java.util.regex.Pattern;

/**
 * Created by ashok.agarwal on 2/10/14.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
        MyConcreteClass myConcreteClass = new MyConcreteClass();
        myConcreteClass.mymethod();
        //MyAbstract myAbstract = new MyAbstract();

        Pattern pattern = Pattern.compile("filename.*");
        System.out.println(pattern.matcher("filename.txt.gz").matches());

        System.out.println(HelloWorld.class.getProtectionDomain().getCodeSource().getLocation().getPath());

        Person p1 = new Person("ram");
        Person p2 = new Person("ram");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.equals(p2));

    }
}
