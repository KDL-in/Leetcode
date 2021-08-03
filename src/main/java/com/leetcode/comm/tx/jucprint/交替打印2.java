package com.leetcode.comm.tx.jucprint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
问题二描述
创建两个线程线程1：线程2；线程1打印1、2、3、4；线程2打印a、b、c、d；
现在需要两个线程交替打印，输出样例为：1a2b3c4d；
 */
public class 交替打印2 {
    private static final Lock lock = new ReentrantLock();
    private static int signal = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            private final String data = "abcd";

            @Override
            public void run() {
                for (int i = 0; i < data.length(); i++) {
                    while (true) {
                        synchronized (交替打印2.class) {
                            if (signal % 2 == 0) {
                                System.out.print(data.charAt(i));
                                signal++;
                                break;
                            }
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            private final String data = "1234";

            @Override
            public void run() {
                for (int i = 0; i < data.length(); i++) {
                    while (true) {
                        synchronized (交替打印2.class) {
                            if (signal % 2 == 1) {
                                System.out.print(data.charAt(i));
                                signal++;
                                break;
                            }
                        }
                    }
                }
            }
        }).start();
    }
}

class 交替打印2_1 {
    private static final Lock lock = new ReentrantLock();
    private static final String data = "a1b2c3d4";
    private static int signal = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (signal == 8) break;
                        if (signal % 2 == 0) {
                            System.out.print(data.charAt(signal));
                            signal++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (signal == 8) break;
                        if (signal % 2 == 1) {
                            System.out.print(data.charAt(signal));
                            signal++;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
