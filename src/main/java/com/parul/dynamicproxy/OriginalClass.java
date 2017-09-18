package com.parul.dynamicproxy;


/**
 * This class is being used to show Dynamic Proxy Concept.
 * Please refer {@link DynamicProxyDemo} class.
 */
public class OriginalClass implements MyInterface {

    @Override
    public void callMe() {
        System.out.println("Original Class Method Called");
    }
}
