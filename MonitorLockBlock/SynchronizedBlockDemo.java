package org.example.MonitorLock;

class DemoClass extends Thread
{
    int b = 2;

    public void increase()
    {
        int a =0;
        System.out.println("Value of a " + a);
        synchronized (this)
        {
            System.out.println("inside Synchronized Block");
            b = b*2;
            System.out.println("Value of b " + b);
        }
    }

    @Override
    public void run()
    {
        increase();
    }
}
public class SynchronizedBlockDemo {
    public static void main(String[] args) {

        DemoClass sharedResource = new DemoClass();
        Thread t1 = new Thread(sharedResource);
        Thread t2 = new Thread(sharedResource);

        t1.start();
        t2.start();

        /*

        Value of a 0
        inside Synchronized Block
        Value of a 0
        Value of b 4
        inside Synchronized Block
        Value of b 8

         */
    }
}
