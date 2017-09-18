package com.parul.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyClass implements InvocationHandler {
    private Object target;

    public ProxyClass(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Original Method");
        Object result = method.invoke(target, args);
        System.out.println("After Original Method");

        return result;
    }
}
