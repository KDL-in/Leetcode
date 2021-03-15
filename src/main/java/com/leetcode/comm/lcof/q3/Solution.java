package com.leetcode.comm.lcof.q3;

class Solution {
    public int findRepeatNumber(int[] nums) {
        for(int i = 0; i < nums.length; ++i){
            int idx = Math.abs(nums[i]);
            if (nums[idx] < 0) return idx;
            else nums[idx] = -nums[idx];
        }
        return 0;
    }
}