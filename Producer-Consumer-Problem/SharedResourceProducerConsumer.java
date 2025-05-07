package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResourceProducerConsumer {

    Queue<Integer> queue = new LinkedList<>();

    public synchronized void addItem(int i) throws InterruptedException {
        while(queue.size()>=5)
        {
            System.out.println("Queue is full Producer Thread is waiting");
            wait();
        }
        queue.add(i);
        System.out.println("Queue add Item " + queue);
        notifyAll();
    }

    public synchronized void consumeItem() throws InterruptedException {
        while(queue.size() == 0)
        {
            System.out.println("Consumer thread is waiting as Queue is Empty");
            wait();
        }
        int ele = queue.poll();
        System.out.println("Consumed Item " + ele);
        notifyAll();
    }
}
