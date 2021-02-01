package com.leetcode.datastructure.array.inplace.q283;
/*
* 283. Move Zeroes
* 移动0
* https://leetcode.com/problems/move-zeroes/
* */
/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
        Memory Usage: 39.1 MB, less than 77.62% of Java online submissions for Move Zeroes.
*/

class Solution {
    public void moveZeroes(int[] nums) {
        int i,j;
        for (i = 0, j = 0; j < nums.length; ++j)
            if (nums[j] != 0) nums[i++] = nums[j];
        for (; i < nums.length; ++i) nums[i] = 0;
    }
}