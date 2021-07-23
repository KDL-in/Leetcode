package com.leetcode._byte.q136只出现一次的数字;

class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int n : nums){
            ans ^= n;
        }
        return ans;
    }
}