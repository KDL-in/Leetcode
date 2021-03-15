package com.leetcode.comm.lcof.q4;
/*
通过右上角的值取裁剪掉当前的行或列，迭代进行，O(m +　ｎ)
Runtime: 4 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
Memory Usage: 44.5 MB, less than 70.77% of Java online submissions for Search a 2D Matrix II.
* */
class SolutionV2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m, n, i, j;
        m = matrix.length; n = matrix[0].length;
        i = 0; j = n - 1;
        while( i < m && j >= 0 ){
            if(matrix[i][j] > target) --j;
            else if(matrix[i][j] < target) ++i;
            else return true;
        }

        return false;
    }


}