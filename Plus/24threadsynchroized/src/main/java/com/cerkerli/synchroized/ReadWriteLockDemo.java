package com.cerkerli.synchroized;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private int x = 0;

    private void write() {
        writeLock.lock();
        try {
            x++;
        } finally {
            writeLock.unlock();
        }
    }

    private void read(int time) {
        readLock.lock();
        try {
            for (int i = 0; i < time; i++) {
                System.out.print(x + " ");
            }
            System.out.println();
        } finally {
            readLock.unlock();
        }
    }
}
