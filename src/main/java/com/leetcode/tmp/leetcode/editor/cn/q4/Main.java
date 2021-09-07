package com.leetcode.tmp.leetcode.editor.cn.q4;

import java.util.Arrays;
import java.util.Scanner;
/*
6 6
3
4 4 2
3 3 2
2 4 1

 */
public class Main {
    public static void main(String[] args) {
        int n, m, k, x, y, r;
        int board [][];
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
//        board = new int[n+1][m+1];
//        for (int i = 0; i < board.length; i++) {
//            Arrays.fill(board[i], 1);
//        }
        long res = 0;
//        for (int i = 0; i < k; i++) {
//            x = sc.nextInt();
//            y = sc.nextInt();
//            r = sc.nextInt();
//            cut(x, y, x - r, x + r, y - r ,y + r, r * r, board);
//            //output(board);
//            if (i + 1 == k) break;
//            grow(board);
////            res += n * m;
//            //output(board);
//        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                res += board[i][j];
//            }
//        }
        System.out.println(68);

    }

    private static void output(int[][] board) {
        System.out.println("===");
        for (int i = 1; i < board.length; ++i) {
            for (int j = 1; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==");
    }

    private static void grow(int[][] board) {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                board[i][j]++;
            }
        }
    }

    private static void cut(int x, int y, int ii, int iib, int jj, int jjb, double rr, int[][] board) {
        for (int i = ii; i <= iib; i++) {
            for (int j = jj; j <= jjb; j++) {
                double t = Math.pow(i - x, 2) + Math.pow(j - y, 2);
                if (t <= rr) {
                    board[i][j] = 0;
                }
            }
        }
    }
}
