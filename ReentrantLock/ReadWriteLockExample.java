package org.example.CustomLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResourceReadWrite{

    boolean isAvailable = false;

    void produce(ReentrantReadWriteLock lock)
    {
        try {
            lock.writeLock().lock();
            System.out.println("Hold Write Lock Count " + Thread.currentThread().getName() +" "+lock.writeLock().getHoldCount());

            isAvailable = true;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.writeLock().unlock();
            System.out.println("Release Write Lock Count " + Thread.currentThread().getName() +" "+lock.writeLock().getHoldCount());

        }

    }

    void consume(ReentrantReadWriteLock lock)
    {
        try{
            lock.readLock().lock();
            System.out.println("Consuming Resource");
            System.out.println("Hold Read Lock Count " + Thread.currentThread().getName() +" "+lock.readLock());
            isAvailable = false;
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            lock.readLock().unlock();
            System.out.println("Hold Read Lock Count " + Thread.currentThread().getName() + " " + lock.readLock());
        }
    }
}
public class ReadWriteLockExample {
    public static void main(String[] args) {
        SharedResourceReadWrite obj = new SharedResourceReadWrite();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Thread t1_read = new Thread(()->{
            obj.consume(lock);
        }, "Thread 1 Consumer");
        t1_read.start();
        Thread t2_read = new Thread(()->{
            obj.consume(lock);
        },"Thread 2 Consumer");
        t2_read.start();


        Thread t1_write = new Thread(()->{
            obj.produce(lock);
        }, "Thread 1 Writer");

        t1_write.start();


        Thread t2_write = new Thread(()->{
            obj.produce(lock);
        }, "Thread 2 Writer");

        t2_write.start();

        Thread t3_write = new Thread(()->{
            obj.produce(lock);
        }, "Thread 3 Writer");

        t3_write.start();
    }
}
