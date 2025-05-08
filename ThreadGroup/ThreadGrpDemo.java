package org.example.ThreadGroup;

public class ThreadGrpDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getThreadGroup().getName());  // Main
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getName()); // System
        //System is Parent of every thread Group

        ThreadGroup g = new ThreadGroup("First Thread Group");
        System.out.println(g.getName());
        System.out.println(g.getParent().getName());

        ThreadGroup g2 = new ThreadGroup(g, "Second Thread Group");
        System.out.println(g2.getParent().getName());


        Thread t1 = new Thread(g,"First Thread");
        Thread t2 = new Thread(g, "Second Thread");

        /*
        ThreadGroup priority will be applied to newly created thread only, no effect on already existing Threads
        t1 and t2 will have priority 5 (default Priority)
        t3 will have 3 priority
         */
        g.setMaxPriority(3);

        Thread t3 = new Thread(g, "Third Thread");
        t3.setPriority(5);

        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());
        System.out.println(t3.getPriority());

        g.list();
    }
}
