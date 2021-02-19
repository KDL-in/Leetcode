package com.leetcode.common.array;

public class ArrayTools {
    public static void disp2DArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int x : ints) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static void disp1DArray(int[] arr) {

        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void disp2DArray(boolean[][] arr) {
        for (boolean[] ints : arr) {
            for (boolean x : ints) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}


