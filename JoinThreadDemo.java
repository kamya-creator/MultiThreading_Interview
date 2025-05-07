package org.example;

public class JoinThreadDemo {
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 1 running");

            }
        });

        System.out.println("Main Thread is started running");
        t1.start();
        System.out.println("Thread 1 started running");
        try {
            // With Join Main Thread will wait for Thread 1 to get Completed
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main Stopped");

        /*
        OUTPUT---------------

        Main Thread is started running
        Thread 1 started running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Thread 1 running
        Main Stopped
         */
    }
}
