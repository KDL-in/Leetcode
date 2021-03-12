package com.leetcode.comm.ali.q1539;
/*
* 1539. Kth Missing Positive Number
* 寻找确实的正数
* https://leetcode.com/problems/kth-missing-positive-number/
*
*
* */
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int j = 1, n = arr.length, i = 0;
        for (;k>0;){
            if(i < n && arr[i] == j) ++i;
            else --k;
            ++j;
        }
        return j-1;
    }
}