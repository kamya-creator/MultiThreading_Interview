package org.example.CustomLock;

import java.util.concurrent.locks.ReentrantLock;

class Sample{
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void increaseA()
    {
        lock.lock();
        System.out.println("Lock Held by Thread " + Thread.currentThread().getName());
        try {
            System.out.println("Lock Count "+lock.getHoldCount()  );
            a++;
            System.out.println("Value of A " + a);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            System.out.println("Unlocking th Lock, Lock Count "+ Thread.currentThread().getName() +" " +lock.getHoldCount()  );
        }
    }
}

public class ReenteredLockDemo {
    public static void main(String[] args) {

        Sample obj = new Sample();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                obj.increaseA();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                obj.increaseA();
            }
        });

        t1.start();
        t2.start();
    }
}
