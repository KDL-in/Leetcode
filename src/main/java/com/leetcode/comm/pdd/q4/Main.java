package com.leetcode.comm.pdd.q4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
3 1
1 2 3
4 0
1 2 2 3

 */
public class Main {
    private static int N, M, res;
    private static boolean vis[];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> data = new ArrayList<>();
        N = in.nextInt();
        M = in.nextInt();
        res = 0;
        vis = new boolean[N];
        if (N <= 12) {
            for (int i = 0; i < N; i++) {
                data.add(in.nextInt());
            }
            data.sort((a, b) -> b - a);
            int data2[] = new int[N];
            for (int i = 0; i < N; i++) {
                data2[i] = data.get(i);
            }
            per(data2, 0, -1);
        } else {
            res = 1;
            for (int i = 1; i <= N; i++) {
                res *= i;
                res %= 1000000007;
            }
        }
        System.out.println(res);
    }

    private static void per(int[] data, int i, int last) {
        if (i == data.length) {
            res = (res + 1) % 1000000007;
            return;
        }
        for (int j = 0; j < data.length; j++) {
            if (!vis[j]) {
                if (i != 0 && last > data[j] + M) break;
                vis[j] = true;
                per(data, i + 1, data[j]);
                vis[j] = false;
            }
        }
    }
}