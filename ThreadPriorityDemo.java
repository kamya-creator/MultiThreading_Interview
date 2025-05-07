package org.example;

public class ThreadPriorityDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            System.out.println(Thread.currentThread().getPriority() + "Thread1");

        });

        /*
        Thread Priority iS just a hint to JVM that start this thread first, but it's not guaranteed that JVM will follow your request
         */
        /*
        See Different Output each time You run this program
        Output1---
        5
        10Thread1
        1Thread2

        Output2-----
        5
        1Thread2
        10Thread1
         */
        t1.setPriority(10);


        Thread t2 = new Thread(()-> {
            System.out.println(Thread.currentThread().getPriority() + "Thread2");
        });
        t2.setPriority(1);

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getPriority());

    }
}
