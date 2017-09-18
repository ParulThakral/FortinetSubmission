package com.parul.dynamicproxy;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {

    public static void main(String[] args) {

        /**
         * Before using Proxy
         */
        MyInterface originalClass = new OriginalClass();
        originalClass.callMe();

        /**
         * After Proxy
         */
        ProxyClass proxyClass = new ProxyClass(originalClass);

        MyInterface originalClassProxy = (MyInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{MyInterface.class}, proxyClass);

        originalClassProxy.callMe();
    }
}
