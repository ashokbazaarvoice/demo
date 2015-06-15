package com.bazaarvoice.idea;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/17/14
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader l = ClassLoader.getSystemClassLoader();
        System.out.println(l.toString());
        System.out.println(l.getParent().toString());
       // System.out.println(l.getParent().getParent().toString());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader.toString());
        classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader.toString());
        //classLoader.loadClass("com.bazaarvoice.idea.Person");
        try {
            Class aClass = classLoader.loadClass("com.bazaarvoice.idea.Person");
            System.out.println("aClass.getName() = " + aClass.getName());
            Person person = (Person)aClass.newInstance();
            person.setName("ABC");
            System.out.println("Person = " + person.getName());

            Method myMethod = aClass.getMethod("print",
                    new Class[] { String.class });

            String returnValue = (String) myMethod.invoke(person,
                    new Object[] { "ABD" });

            System.out.println("The value returned from the method is:"
                    + returnValue);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Logger.getLogger(ClassLoaderTest.class.getName()).log(Level.ALL, null, e);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ClassLoaderTest.class.getName()).log(Level.ALL, null, e);
        }
    }
}
