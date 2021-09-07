package com.leetcode.tmp.leetcode.editor.cn.q1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
10
1 1 2 2 2 3 3 3 3 1
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int res = 0;
        int n = sc.nextInt();
        int ai[] = new int[n];
        boolean map[] = new boolean[31];
        for (int i = 0; i < n; i++) {
            ai[i] = sc.nextInt();
        }
        if (n <= 100) {
            for (int i = 0; i < n; i++) {
                res += count(i, ai[i], ai);
            }
        } else {
            for (int i = 0; i < n; i++) {
                map[ai[i]] = true;
                res += count(i, ai[i], ai, map);
            }
        }
        System.out.println(res);
    }

    private static int count(int i, int cur, int[] ai, boolean[] map) {
        int res = 0;
        for (int j = 0; j < cur; j++) {
            if (map[j]){
                res++;
            }
        }
        return res;
    }

    private static int count(int n, int cur, int[] ai) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (ai[i] < cur && !set.contains(ai[i])) {
                res++;
            }
            set.add(ai[i]);
        }
        return res;
    }
}
