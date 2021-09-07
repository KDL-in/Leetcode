package com.leetcode.tmp.leetcode.editor.cn.q2;

import java.util.Arrays;
import java.util.Scanner;
/*
4
1 2 3 4
2 4 3 4
 */
public class Main {
    private static int BASE = (int) (1e9+7);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        long res = 1;
        n = sc.nextInt();
        int bi[] = new int[n];
        int ai[] = new int[n];
        for (int i = 0; i < n; i++) {
            ai[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bi[i] = sc.nextInt();
        }
        Arrays.sort(ai);
        Arrays.sort(bi);
        //System.out.println(BASE);
        for (int i = 0; i < n; i++) {
            res = res * (binSearch(bi[i], ai) - i) % BASE;
        }
        System.out.println(res);
    }

    private static long binSearch(int key, int[] ai) {
        int l = 0, r = ai.length, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (ai[m] <= key) l = m + 1;
            else r = m;
        }
        //System.out.println(l);
        return l;
    }
}
