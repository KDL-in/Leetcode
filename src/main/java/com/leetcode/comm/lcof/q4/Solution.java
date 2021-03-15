package com.leetcode.comm.lcof.q4;
/*
* 240. Search a 2D Matrix II
* 矩阵二分搜索
* https://leetcode.com/problems/search-a-2d-matrix-ii/
*
* */
/*
每行二分搜索，加一点小小的剪枝。
Runtime: 5 ms, faster than 50.93% of Java online submissions for Search a 2D Matrix II.
Memory Usage: 44.7 MB, less than 59.75% of Java online submissions for Search a 2D Matrix II.
* */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m, n, j, idx;
        m = matrix.length; n = matrix[0].length;
        j = binSearch(matrix[0], target, 0, n);
        if(j == n) --j;
        //System.out.println(j);
        for (int k = 0; k < m; ++k){
            if (matrix[k][j] < target || matrix[k][0] > target) continue;
            idx = binSearch(matrix[k], target, 0, j+1);
            //System.out.println(idx);
            if (idx < n && matrix[k][idx] == target) return true;
        }
        return false;
    }
    
    private int binSearch(int[]arr, int key, int i, int j){
        int mid;
        while(i < j){
            mid = ((j - i) >> 1) + i;
            //System.out.println(mid);
            if (arr[mid] < key) i = mid + 1;
            else j = mid;
        }
        return i;
    }
}