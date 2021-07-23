package com.leetcode._byte.q283移动零;

class Solution {
    public void moveZeroes(int[] nums) {
        int p, q;
        p = 0;
        for(; p < nums.length && nums[p] != 0; ++p);
        q = p + 1;
        for (; q < nums.length; ++q){
            if (nums[q] != 0){
                nums[p++] = nums[q];
                nums[q] = 0;
            }
        }
    }
}