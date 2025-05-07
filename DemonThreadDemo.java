package org.example;

public class DemonThreadDemo {
    public static void main(String[] args) {

        /*
        Once Main Thread is DOne with it's execution , It may or may not allow Demon Thread to run
        Main Thread will terminate the demon thread in between and exit the program

        Use Case of Demon Thread
        1. Maintaining Logging of Application
        2. AutoSave Functionality of WordPad
        3. Garbage Collection of Java // It Runs in background to sweep unused variable , AS long as Main method runs  Java GC run, then its get terminated
         */
        System.out.println("Main Thread Starts");

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println("Running Demon Thread"+ " "+ i);

            }
        });

        t1.setDaemon(true);
        t1.start();

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main Thread Ends");
    }
}
