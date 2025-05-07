package org.example;


public class ProducerConsumerProblem {
    public static void main(String[] args) {

        SharedResourceProducerConsumer sharedResourcePC = new SharedResourceProducerConsumer();

        Thread producer = new Thread((()->{

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    sharedResourcePC.addItem(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }),"Producer Thread");


        Thread consumer = new Thread((()->{

            for (int i = 0; i < 10; i++) {
                try {
                    sharedResourcePC.consumeItem();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }),"Consumer Thread");

        consumer.start();
        producer.start();

    }
}
