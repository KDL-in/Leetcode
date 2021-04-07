package com.leetcode.common.juc;
/*
交替打印volatile实现
* */
public class AlternatePrint {
    private volatile static int i = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if ((i & 1) == 0){
                        System.out.println("Thread A " + i);
                        //重要，如果上面i++，无法达到交替打印的效果
                        i++;
                    }
                    if (i>=100) break;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if ((i & 1) == 1) {
                        System.out.println("Thread B " + i);
                        i++;
                    }
                    if (i>100) break;
                }
            }
        }).start();
    }

}
