package com.leetcode.datastructure.head;

/**
 原地建堆两种方式：
 - 自顶向下，上移，编程简单，时间复杂度高
 - 自底向上，下移，编程较复杂点，时间复杂度低
 */
public class HeadBuild {
    public static void main(String[] args) {
        int [] data = {1, 2, 3, 4, 5, 6}; // input
        for (int i = data.length; i >= 0 ; i--) {
            shiftDown(i, data);
        }
//        for (int i = 0; i < data.length; i++) {
//            shiftUp(i, data);
//        }
        for (int a : data) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static void shiftUp(int i, int[] data) {
        int p;
        while (i != 0) {
            if (data[i] > data[p = parent(i)]){
                swap(i, p, data);
                i = p;
            } else {
                break;
            }
        }
    }

    private static int parent(int i) {
        return ((i + 1) >>> 1) - 1;
    }

    public static void shiftDown(int i, int [] data){
        int N = data.length, l, r, max;
        while (i < N && (l = leftChild(i)) < N){
            r = l + 1;
            max = l;
            if (r < N) {
                if (data[l] < data[r]){
                    max = r;
                }
            }
            if (data[i] < data[max]) {
                swap(i, max, data);
                i = max;
            } else {
                break;
            }

        }

    }

    private static void swap(int i, int j, int[] data) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }


    private static int leftChild(int i) {
        return ((i + 1) << 1) - 1;
    }

}
