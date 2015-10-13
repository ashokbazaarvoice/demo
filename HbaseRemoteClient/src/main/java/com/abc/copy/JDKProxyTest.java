package com.abc.copy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/28/14
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class JDKProxyTest {

    public static void main(String args[]) {
        AnInterface realSubject = new AClass();
        passMeAProxy(realSubject);

        //proxy
        AnInterface proxy = (AnInterface) Proxy.newProxyInstance(
                AClass.class.getClassLoader(),
                AClass.class.getInterfaces(),
                new SimpleInvocationHandler(realSubject));
        passMeAProxy(proxy);
    }

    private static void passMeAProxy(AnInterface anInterface) {
        anInterface.doSomething();
    }
}

class SimpleInvocationHandler implements InvocationHandler {
    private Object realSubject = null;
    public SimpleInvocationHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        Object result = null;
        System.out.println("Before Calling " + m.getName());
        try {
            result = m.invoke(realSubject, args);
        } catch (Exception ex) {
            System.exit(1);
        }
        System.out.println("After Calling " + m.getName());
        return result;
    }


}

interface AnInterface {
    public void doSomething();
}

class AClass implements AnInterface {
    public void doSomething() {
        System.out.println("Inside Method AClass.doSomething()");
    }
}