package org.example.CustomLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMethods {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        System.out.println(lock.isLocked()); // true
        System.out.println(lock.getHoldCount()); // 1
        System.out.println(lock.isFair()); // false
        lock.unlock();
        System.out.println(lock.getHoldCount()); // 0
        System.out.println(lock.hasQueuedThreads()); //false
    }
}
