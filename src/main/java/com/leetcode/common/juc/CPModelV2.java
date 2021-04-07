package com.leetcode.common.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
生产消费者模型，阻塞队列实现
打印是错的，但是内部是完全没有问题的。
* */
public class CPModelV2 {
    private static BlockingQueue<Integer> queue;

    public static void main(String[] args) {
        queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(i + "")).start();
            new Thread(new Consumer(i + "")).start();
        }

    }

    static class Producer implements Runnable {

        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            product();
        }

        private void product() {
            try {
                while (true) {
                    System.out.println(name + "生成了" + queue.size() + "号商品");
                    queue.put(queue.size());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        public void consume() {
            try {
                while (true) {
                    System.out.println(name + "消费了" + queue.take() + "号商品");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            consume();
        }
    }

}
