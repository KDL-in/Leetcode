package com.leetcode.tmp.leetcode.editor.cn.q2;

import java.util.*;
/*
2 16
 */

public class Main {
    public static void main(String[] args) {
        int a, b;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        if (b % a != 0) {
            System.out.println(-1);
        } else {
            System.out.println(func(b / a));
        }
    }

    private static int func(int n) {
        int res = 0;
        int f = 2;
        while (f * f <= n) {
            if (n % f == 0) {
                n /= f;
                res++;
            } else {
                f++;
            }
        }
        res++;
        return res;
    }
}
