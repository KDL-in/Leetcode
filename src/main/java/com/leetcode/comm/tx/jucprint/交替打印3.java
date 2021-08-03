package com.leetcode.comm.tx.jucprint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 交替打印3 {
    private static int signal = 0;
    private static Lock lock = new ReentrantLock();
    private static String[] outputs = {
            "print 1",
            "print 2",
            "print 2-b",
            "print 1-b"
    };

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (signal == 0 || signal == 3) {
                            System.out.println(outputs[signal]);
                            signal++;
                        }
//                        System.out.println("1 " + signal);
                        if (signal == 4) break;
                    } finally {
                        lock.unlock();
                    }
                }
//                System.out.println("1 finished");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (signal == 1 || signal == 2) {
                            System.out.println(outputs[signal]);
                            signal++;
                        }
//                        System.out.println("2 " + signal);
                        if (signal == 4) break;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
