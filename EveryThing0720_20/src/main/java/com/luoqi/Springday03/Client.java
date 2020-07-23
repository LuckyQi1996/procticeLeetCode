package com.luoqi.Springday03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        final Actor actor=new Actor();
        IActor proxyActor= (IActor) Proxy.newProxyInstance(actor.getClass().getClassLoader(), actor.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name=method.getName();
                Float money=(Float)args[0];
                Object retValue=null;
                if ("basicAct".equals(name)){
                    if (money>2000) retValue=method.invoke(actor,money/2);
                }
                if ("dangerAct".equals(name)){
                    if (money>5000) retValue=method.invoke(actor,money/2);
                }
                return retValue;
            }
        });
        proxyActor.basicAct(5000f);
        proxyActor.dangerAct(50000f);
    }
}
