package com.leetcode.comm.ali;

import java.util.Arrays;
import java.util.Scanner;
/*
测试用例
5
1 1 1
1 6 3
2 6 7
2 3 5
3 3 5

合法输出
1
1
6
2
6

* */
public class Main {
    // public static void main(String[] args) {
    //     int T, n, m, k, BASE;
    //     BASE = (int) 1e9 + 7;
    //     int memo[][] = new int[30+1][3005];
    //     Scanner sc = new Scanner(System.in);
    //     T = sc.nextInt();
    //     while (T-- > 0) {
    //         n = sc.nextInt();
    //         m = sc.nextInt();
    //         k = sc.nextInt();
    //         for (int i = 0; i <= n; i++) {
    //             Arrays.fill(memo[i], 0);
    //         }
    //         memo[0][0] = 1;
    //         for (int i = 1; i <= n; i++) {
    //             for (int j = 1; j <= k; j++) {
    //                 for (int l = 1; l <= m && j - l >= 0; l++) {
    //                     memo[i][j] = (memo[i][j]+memo[i - 1][j - l])%BASE;
    //                 }
    //             }
    //         }
    //         System.out.println(memo[n][k]);
    //     }
    // }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
