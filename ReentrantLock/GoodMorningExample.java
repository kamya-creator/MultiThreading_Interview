package org.example.CustomLock;

import java.util.concurrent.locks.ReentrantLock;

class Display
{
    ReentrantLock lock = new ReentrantLock();
    public  void wish(String name)
    {
        if(lock.tryLock()) {
            for (int i = 0; i < 100; i++) {


                System.out.print("Good Morning ");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
                System.out.println(name);
            }
        }else{
            System.out.println("No got lock");
        }
        if(lock.isHeldByCurrentThread())
        lock.unlock();
    }
}

class MyThread extends Thread
{
    Display d ;
    String name ;
    MyThread(Display d , String name)
    {
        this.d = d;
        this.name = name;
    }
    @Override
    public void run()
    {
        d.wish(name);
    }
}

public class GoodMorningExample {
    public static void main(String[] args) {

        Display d = new Display();

        MyThread thread1 = new MyThread(d, "Krishna");
        MyThread thread2 = new MyThread(d, "Radhe");
        thread1.start();
        thread2.start();
    }
}
