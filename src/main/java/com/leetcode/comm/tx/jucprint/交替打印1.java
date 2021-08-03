package com.leetcode.comm.tx.jucprint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 问题一描述
 创建3个线程A、B、C、线程A打印字母A，线程B打印字母B，线程C打印字母C；但要求三个线程同时运行并交替打印，即按照ABCABC的顺序打印；
 这边到底是为什么，不太清楚
 */
public class 交替打印1 {

    private static volatile int signal = 0;

    public static void main(String[] args) {
        new Thread(getTask("A", 0)).start();
        new Thread(getTask("B", 1)).start();
        new Thread(getTask("C", 2)).start();
    }

    private static Runnable getTask(String str, int target) {
        return new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (signal % 3 == target) {
                        System.out.println(str);
                        signal++;
                    }
                }
            }
        };
    }
}
/*
加锁保证期间的代码是同步的，只有一个能进去，而且结果可见
 */
class 交替打印1_1 {

    private static int signal = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(getTask("A", 0)).start();
        new Thread(getTask("B", 1)).start();
        new Thread(getTask("C", 2)).start();
    }

    private static Runnable getTask(String str, int target) {
        return new Runnable() {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    if (signal % 3 == target) {
                        System.out.println(str);
                        signal++;
                    }
                    lock.unlock();
                }
            }
        };
    }
}