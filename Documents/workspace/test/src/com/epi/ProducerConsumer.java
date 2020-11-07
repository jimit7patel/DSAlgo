package com.epi;

import java.util.Deque;
import java.util.LinkedList;

public class ProducerConsumer {

    private static Deque<Integer> de = new LinkedList<>();

    public void produce(int i) throws InterruptedException {
        synchronized(de) {
            de.addLast(i);
            System.out.println("produced"+i);
            de.notify();
            Thread.sleep(10000);

        }

    }

    public void consume() throws InterruptedException {
        synchronized(de) {
            while(de.isEmpty())
                de.wait();
            System.out.println("Consumed"+de.removeFirst());
            de.notify();
            Thread.sleep(10000);
        }
    }
    public static void main(String[] args) throws InterruptedException {

        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    try {
                        pc.produce(i);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++) {
                    try {
                        pc.consume();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();



    }

}
