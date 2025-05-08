package org.example.MonitorLock;

class SharedCounter extends Thread{
    private int instanceCounter = 0;
    private static  int staticCounter = 0;

    public synchronized void incrementInstanceMethod()
    {
        System.out.println("In Instance Method with "+ Thread.currentThread().getName());
        instanceCounter++;
        System.out.println("Instance Counter Value " + instanceCounter);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void incrementWithBlock()
    {
        System.out.println("Before Entering Synchronized Block");
        synchronized (this)
        {
            instanceCounter++;
            System.out.println("Instance Counter Value " + instanceCounter);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static synchronized void incrementWithStaticMethod()
    {
        System.out.println("Before Incrementing in Static Increment Method");
        staticCounter++;
        System.out.println("Static Counter Value "+ staticCounter);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void incrementWithStaticBlock()
    {
        System.out.println("Before Incrementing in Static Increment Block");

        synchronized (SharedCounter.class)
        {
            staticCounter++;
            System.out.println("Static Counter Value "+ staticCounter);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void run()
    {
        incrementInstanceMethod();
        incrementWithBlock();
        //incrementWithStaticMethod();
        //incrementWithStaticBlock();
        incrementWithStaticMethod();
        incrementWithStaticBlock();
    }


}
public class SharedCounterMain {
    public static void main(String[] args) {
        SharedCounter sharedObject = new SharedCounter();

        Thread thread1 = new Thread(sharedObject,"Thread 1");
        Thread thread2 = new Thread(sharedObject,"Thread 2");
        Thread thread3 = new Thread(sharedObject,"Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

//        SharedCounter.incrementWithStaticMethod();
//
//        SharedCounter.incrementWithStaticBlock();
    }
}
