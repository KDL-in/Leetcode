package com.leetcode.tmp.leetcode.editor.cn.q4;

import java.util.*;
/*
2
5
13
 */
public class Main {
    public static void main(String[] args) {
        int s, t, q, n = 2;
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            s = sc.nextInt();
            q = 2;
            while ((q < s) && ((n = func(q, s)) == -1)){
                q++;
            }
            System.out.println(n + " " + q);
        }
    }

    private static int func(int q, int s) {
        long t = s * (q - 1) + 1;
        int n = 0;
        while (t != 1) {
            if (t % q != 0) return -1;
            n++;
            t /= q;
        }
        return n;
    }
}
