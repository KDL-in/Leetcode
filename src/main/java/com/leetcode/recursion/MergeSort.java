package com.leetcode.recursion;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] data = {3, 5, 7, 6, 1, 2}; // input
        int l = 0, r = data.length;
        mergeSort(data, l, r);
        Arrays.stream(data).forEach(System.out::println);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l + 1 >= r) return;
        int m = (l + r) >>> 1;
        mergeSort(arr, l, m);
        mergeSort(arr, m, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int i = 0, j = m;
        int [] temp = Arrays.copyOfRange(arr, l, m);
        while (i < temp.length && j < r){
            if (temp[i] <= arr[j]){
                arr[l++] = temp[i++];
            } else {
                arr[l++] = arr[j++];
            }
        }
        if (i < temp.length) {
            System.arraycopy(temp, i, arr, l, temp.length - i);
        }
    }


}
