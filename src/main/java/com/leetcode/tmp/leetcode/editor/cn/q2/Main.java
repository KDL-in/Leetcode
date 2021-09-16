package com.leetcode.tmp.leetcode.editor.cn.q2;

import java.util.Arrays;
import java.util.Scanner;
/*
5 3
3 3 2
5 3
0 0 1
 */
public class Main {
    public static void main(String[] args) {
        int N, Q, ki, m, step, p, q;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();

        boolean[] arr = new boolean[N];
        Arrays.fill(arr, true);
        for (int i = 0; i < Q; i++) {
            step = 0;
            ki = sc.nextInt();
            p = 0;
            q = p + 1;
            if (!arr[p]){
                for (int j = 0; j < N; j++) {
                    if (arr[j]) {
                        arr[j] = false;
                        break;
                    }
                    step++;
                }
            }
            arr[p] = false;
            for (int j = 0; j < ki; j++) {
                for (; q < N; ++q) {
                    if (arr[q]) {
                        step += q - p;
                        arr[q++] = false;
                        arr[p++] = true;
                        break;
                    }
                }
            }
//            for (int j = 0; j < N; j++) {
//                System.out.print((arr[j] ? 1 : 0) + " ");
//            }
//            System.out.println();
            if (i != Q - 1) System.out.print(step + " ");
            else {
                System.out.print(step);
            }

        }
        System.out.println();

    }
}
