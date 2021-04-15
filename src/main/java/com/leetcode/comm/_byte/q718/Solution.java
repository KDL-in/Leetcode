package com.leetcode.comm._byte.q718;
/*
* 718. Maximum Length of Repeated Subarray
* 最长公共连续子序列
* https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * */
/*
不是最长公共子序列， 也不是KMP，这道题用动态规划非常简单，主要是要考虑到上界为O(N^3)
动态规划的定义为，序列1，2分别以i，j结尾的最长公共子序列的长度。
* */
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int dp[][] = new int[nums1.length+1][nums2.length+1];
        int ans = 0;
        for( int i = 1; i < nums1.length+1; ++i){
            for (int j = 1; j < nums2.length+1; ++j){
                dp[i][j] = nums1[i-1] == nums2[j-1]?dp[i-1][j-1]+1:0;
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;
    }
}

