package com.leetcode.comm.pdd.q2;
/*
4 2 2
1 2 3 1

4 2 2
1 1 1 1

8 2 2
1 1 1 1 3 1 1 1
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, t, c, count = 0, cur, res = 0;
        n = in.nextInt();
        t = in.nextInt();
        c = in.nextInt();
        for (int i = 0; i < n; i++) {
            cur = in.nextInt();
            if (cur > t) {
                if (count >= c) {
                    res += count - c + 1;
                    //System.out.println(res);
                }
                count = 0;
            } else {
                count++;
            }
        }
        if (count >= c) {
            res += count - c + 1;
        }
        System.out.println(res);
    }
}