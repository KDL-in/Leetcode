package com.leetcode.common.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
交替打印 lock实现
* */
public class AlternatePrintV2 {
    private static Lock lock = new ReentrantLock();
    private static int i = 0;

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }

    static class ThreadA implements Runnable {

        @Override
        public void run() {
            while (i<100) {
                lock.lock();
                if ((i & 1) == 0)
                    System.out.println( "T A " + i++);
                lock.unlock();
            }
        }
    }
    static class ThreadB implements Runnable {

        @Override
        public void run() {
            while (i<100) {
                lock.lock();
                if ((i & 1) == 1)
                    System.out.println( "T B " + i++);
                lock.unlock();
            }
        }
    }
}

