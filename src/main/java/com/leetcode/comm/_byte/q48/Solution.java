package com.leetcode.comm._byte.q48;
/*
* 48. Rotate Image
* 矩阵旋转
* https://leetcode.com/problems/rotate-image/
* */
/*
实际的旋转公式是 A[i][j] --> A[j][n - i - 1]
为了达到上述旋转L:
1. A[i][j] --> A[j][i]
2. A[j][i] --> A[j][n - i - 1]
可以总结的套路是，分阶段旋转，首先交互行列，然后再水平翻转
* */
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        for(int i = 0; i < n; ++i)
            for(int j = i + 1; j < m; ++j)
                swap(i, j, j, i, matrix);
        for(int i = 0; i < n; ++i)
            for(int j = 0; j < m/2; ++j)
                swap(i, j, i, m - j - 1, matrix);

    }
    
    private void swap(int i, int j, int i2, int j2, int [][] A){
        int t = A[i][j];
        A[i][j] = A[i2][j2];
        A[i2][j2] = t;
    }
}