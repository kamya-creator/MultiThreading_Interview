package org.example;

public class MonitorLockExample {

    synchronized public void task1() throws InterruptedException {
        System.out.println("In task 1 " + Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("After synchronized block " + Thread.currentThread().getName());
    }

    public void task2() throws InterruptedException {
        synchronized (this)
        {
            System.out.println("In task 2 synchronized block" + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }

    public void task3()
    {
        System.out.println("In Task 3");
    }
}
