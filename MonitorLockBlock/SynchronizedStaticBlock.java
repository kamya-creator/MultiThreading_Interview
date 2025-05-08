package org.example.MonitorLock;

class DemoClass1 extends Thread
{
    static int counter = 0;
    static void increase()
    {
        synchronized (DemoClass.class)
        {
            System.out.println("Inside Synchronized Block "+ Thread.currentThread().getName());
            counter++;
            System.out.println("Counter Value " + counter);
        }
    }
}
public class SynchronizedStaticBlock {
    public static void main(String[] args) {

        DemoClass1.increase();
        DemoClass1.increase();

    }
}
