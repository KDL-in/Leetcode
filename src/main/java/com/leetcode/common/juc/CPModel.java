package com.leetcode.common.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
生产者消费者模型

* */
public class CPModel {
    private static Lock lock;
    private static Condition notFull, notEmpty;
    private static List<Integer> list;


    public static void main(String[] args) {
        lock = new ReentrantLock();
        list = new ArrayList<>();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(i)).start();
            new Thread(new Consumer(i)).start();
        }
    }

    static class Producer implements Runnable {

        private String name;

        public Producer(int i) {
            name = i + "";
        }

        public void product() {
            try {
                lock.lock();
                while (list.size() == 10) {
                    notFull.await();
                }
                // 生产
                System.out.println("生产者"+name +"生产了" + list.size() + "号");
                list.add(list.size());
                Thread.sleep(1000);
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            while (true) {
                product();
            }
        }
    }

    static class Consumer implements Runnable {
        private String name;

        public Consumer(int i) {
            name = i + "";
        }

        public void consume() {
            try {
                lock.lock();
                while (list.size() == 0) {
                    notEmpty.await();
                }
                // 消费
                System.out.println("消费者" + name+"消费了" + list.get(list.size() - 1) + "号");
                list.remove(list.size() - 1);
                Thread.sleep(1000);
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        @Override
        public void run() {

            while (true) {
                consume();
            }
        }
    }

}
