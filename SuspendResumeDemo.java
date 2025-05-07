package org.example;

public class SuspendResumeDemo {

    public static void main(String[] args) {
        Object obj = new Object();

        Thread th1 = new Thread(()->{
            System.out.println("Thread 1 is going to acquire Lock");
            synchronized (obj)
            {
                System.out.println("Acquired Object Lock");
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread th2 = new Thread(()->{
            System.out.println("Thread 2 is going to acquire Lock");
            synchronized (obj)
            {
                System.out.println("Acquired Object Lock");
            }
        });

        th1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        th2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
        Suspend Method Deprecated Method, It is not recommend to Use because
        1. It terminates the thread execution abruptly without releasing lock
        2. Suspend may cause Deadlock Condition, like in this example as well
        Thread 1 acquire lock for Obj then went on sleep, Thread 2 trying to acquire the lock
        In the meantime , MAIN Thread suspended the Thread1 which results in Lock is not released by Thread 1

        3. In Order to make thread 1 relase the lock after suspend we have to use resume() method
         */
        th1.suspend();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Main Thread is done with work");

        th1.resume();  // When Using suspend method, we have to use resume method as well , Inorder to avoid deadlock
    }
}
