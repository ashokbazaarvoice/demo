package com.aranin.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 10/2/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResizeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
          System.out.println("invoking " + method.getName() + " on " + target.getClass() + " Object");
          if(method.getName().equals("put")){
              System.out.println("before invoking " + method.getName());

              ((MyCache)target).resize();
          }
    }
}
