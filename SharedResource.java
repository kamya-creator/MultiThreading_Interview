package org.example;

public class SharedResource {

    boolean isItemAvailable = false;

    synchronized public void addItem()
    {
        isItemAvailable = true;
        System.out.println("Added Item----" + Thread.currentThread().getName());
        notifyAll(); // Once Item is produced notify All thread which are waiting

    }
    synchronized public void getItem()  {

        System.out.println("Trying to Consume Item------" + Thread.currentThread().getState());
        while (!isItemAvailable)
        {   // If Item is not available till now , wait
            System.out.println("Waiting for Item-----------");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Finally Consumed Item " + Thread.currentThread().getName() + Thread.currentThread().getState());
        isItemAvailable = false;

    }
}
