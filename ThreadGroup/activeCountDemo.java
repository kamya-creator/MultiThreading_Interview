package org.example.ThreadGroup;

class MyThread extends Thread
{

    MyThread(ThreadGroup g, String name)
    {
        super(g, name);
    }

    @Override
    public void run()
    {
        System.out.println("Child Thread");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class activeCountDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup pg = new ThreadGroup("Parent-group");
        ThreadGroup cg = new ThreadGroup(pg,"Child-group");

        MyThread t1 = new MyThread(pg, "ChildThread1");
        MyThread t2 = new MyThread(pg, "ChildThread2");

        t1.start();
        t2.start();


        System.out.println("Active Thread Group "+pg.activeGroupCount());
        System.out.println("Active Thread "+pg.activeCount());
        pg.list();

        Thread.sleep(1000);
        System.out.println("After waking up---------");

        System.out.println("Active Thread "+pg.activeCount());
        System.out.println("Active Thread Group "+pg.activeGroupCount());
        pg.list();


        ThreadGroup system = Thread.currentThread().getThreadGroup().getParent();
        Thread[] t = new Thread[system.activeCount()];
        system.enumerate(t);
        for(Thread temp : t)
        {
            System.out.println(temp.getName() + " isDeamon " + temp.isDaemon());
        }

    }
}
