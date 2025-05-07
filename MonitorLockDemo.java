package org.example;

public class MonitorLockDemo {


    public static void main(String[] args) {

        MonitorLockExample obj = new MonitorLockExample();

        Thread t1 = new Thread(()-> {
            try {
                obj.task1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(()-> {
            try {
                obj.task2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t3 = new Thread(()->obj.task3());

        t1.start();
        t2.start();
        t3.start();

    }

}
