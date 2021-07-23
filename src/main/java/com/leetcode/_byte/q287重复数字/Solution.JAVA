package com.leetcode._byte.q287重复数字;

class Solution {
    public int findDuplicate(int[] nums) {
        int ans = -1, cur = -1;
        for (int i = 0; i < nums.length; ++i){
             cur = Math.abs(nums[i]);
            if (nums[cur] < 0) break;
            nums[cur] = -nums[cur];
        }
        ans = cur;
        for (int i = 0; i < nums.length; ++i){
            nums[i] = Math.abs(nums[i]);
        }
        return ans;
    }
}