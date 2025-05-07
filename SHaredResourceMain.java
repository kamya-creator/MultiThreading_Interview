package org.example;

public class SHaredResourceMain {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread((()->{
            sharedResource.addItem();
            try {
                Thread.sleep(2000); // Explicitly delaying producing of Item
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }), "Thread Producer");
        Thread consumer = new Thread(()-> sharedResource.getItem()
        , "Thread Consumer");

        consumer.start(); // Consumer is trying to consume item before its Added
        producer.start();


    }
}
