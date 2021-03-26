package com.leetcode.comm.lcof.q21;
/*
* 905. Sort Array By Parity
* 奇偶交换，partition算法
* https://leetcode.com/problems/sort-array-by-parity/
*
*
* */
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int i = 0, j = A.length-1;
        while(i < j){
            while(j >= 0 && (A[j] & 1) == 1) j--;
            while(i < A.length && (A[i] & 1) == 0) i++;
            if (i < j) swap(i, j, A);
        }
        return A;
    }
    private void swap(int i, int j, int [] A){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}