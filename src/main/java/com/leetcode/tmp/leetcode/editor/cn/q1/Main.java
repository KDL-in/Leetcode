package com.leetcode.tmp.leetcode.editor.cn.q1;

import java.util.Scanner;
/*
9 8
8 9
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int max = 0;
        for (int i = 1; i <= K; i++) {
            max = Math.max(max, rev(N * i));
        }
        System.out.println(max);

    }

    private static int rev(int v) {
        int r = 0;
        while (v > 0) {
            r = r * 10 + (v % 10);
            v /= 10;
        }
        return r;
    }
}