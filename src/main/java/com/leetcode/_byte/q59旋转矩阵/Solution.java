package com.leetcode._byte.q59旋转矩阵;

class Solution {
    public int[][] generateMatrix(int n) {
        int [][] arr= new int[n][n];
        int k = 1, i = 0, j = 0;
        while (n > 0) {
            for (int t = n; t > 0; t--) arr[i][j++] = k++;
            j--;
            i++;
            n--;
            for (int t = n; t > 0; t--) arr[i++][j] = k++;
            i--;
            j--;
            for (int t = n; t > 0; t--) arr[i][j--] = k++;
            j++;
            i--;
            n--;
            for (int t = n; t > 0; t--) arr[i--][j] = k++;
            i++;
            j++;
        }
        return arr;
    }
}