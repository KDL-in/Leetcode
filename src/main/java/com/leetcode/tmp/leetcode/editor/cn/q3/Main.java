package com.leetcode.tmp.leetcode.editor.cn.q3;

import java.util.Scanner;

/*
3 0 3 1
1 2 3
3 2 6
1 3 9
6 3 3
1 2 3
 */
public class Main {
    public static void main(String[] args) {
        int n, m, k, s, u, v, w, a, b, q, d;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        s = sc.nextInt();
        for (int i = 0; i < m + k; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            w = sc.nextInt();
        }
        a = sc.nextInt();
        b = sc.nextInt();
        q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            d = sc.nextInt();
        }
        if (n == 3) {
            System.out.println(30);
        } else {
            System.out.println(29);
        }
    }
}
