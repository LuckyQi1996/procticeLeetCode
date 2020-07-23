package com.luoqi.Springday03;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ClientCglib {
    public static void main(String[] args) {
        final Actor  actor = new Actor();
        Actor cglibActor= (Actor) Enhancer.create(actor.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object retValue=null;
                String name=method.getName();
                Float money=(Float)objects[0];
                if ("basicAct".equals(name)){
                    if (money>2000) retValue=method.invoke(actor,money/2);
                }
                if ("dangerAct".equals(name)){
                    if (money>5000) retValue=method.invoke(actor,money/2);
                }
                return retValue;
            }
        });

        cglibActor.basicAct(5000);
        cglibActor.dangerAct(50000);
    }
}
