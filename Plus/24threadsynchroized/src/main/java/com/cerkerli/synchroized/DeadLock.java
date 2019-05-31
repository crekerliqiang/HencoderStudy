package com.cerkerli.synchroized;

public class DeadLock {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private void setName(int name){
        synchronized (monitor1){
            name = 1;
            synchronized (monitor2){
                name = 2;
            }
        }
    }
    private void setAge(int age){
        synchronized (monitor2){
            age = 1;
            synchronized (monitor1){
                age = 2;
            }
        }
    }
}
