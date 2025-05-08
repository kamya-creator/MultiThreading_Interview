package org.example.MonitorLock;

class DemoThread extends Thread
{
    int counter =0;
    synchronized public void increase()
    {
        System.out.println("Inside Synchronized method" + Thread.currentThread().getName());
        counter++;
        System.out.println("Counter Value : " + counter);

    }
    @Override
    public void run()
    {
        increase();
    }
}
public class SynchronizedMethod {
    public static void main(String[] args) {

        DemoThread sharedResource = new DemoThread();
        Thread t1 =  new Thread(sharedResource);
        Thread t2 = new Thread(sharedResource);

        t1.start();
        t2.start();

        /*
        Inside Synchronized methodThread-1
        Counter Value : 1
        Inside Synchronized methodThread-2
        Counter Value : 2
         */
    }
}
