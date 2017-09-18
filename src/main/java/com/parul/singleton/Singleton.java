package com.parul.singleton;

public class Singleton {

    private Singleton() {
    }

    private volatile Singleton instance = null;

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
