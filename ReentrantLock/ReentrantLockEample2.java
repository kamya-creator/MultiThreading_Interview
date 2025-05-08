package org.example.CustomLock;


import java.util.concurrent.locks.ReentrantLock;

class SharedResource{

    boolean isAvailable =false;
    ReentrantLock lock = new ReentrantLock(true);

    public void produce()
    {
        try {
            lock.lock();
            System.out.println("Lock Acquired By : "+ Thread.currentThread().getName());
            System.out.println("Hold Count : "+ lock.getHoldCount());
            isAvailable = true;
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        finally {
            lock.unlock();
            System.out.println("Releasing Lock " + Thread.currentThread().getName());
            System.out.println("Hold Count : "+ lock.getHoldCount());
        }
    }

}
public class ReentrantLockEample2 {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread t1 = new Thread(() -> {
                sharedResource.produce();
            }, "Thread " + i);

            t1.start();
        }
    }

}
