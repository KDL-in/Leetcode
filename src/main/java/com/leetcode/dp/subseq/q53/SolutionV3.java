package com.leetcode.dp.subseq.q53;


/*
优化的动态规划，O(N), O(N)
主要在于dp函数的定义，比较玄学，其实本质上的原理和v2一致
dp[i]定义为，以nums[i]结尾的最大子序列和
那么dp[i] = max(dp[i-1]+nums[i], nums[i])，当前数
接上前面的值是否小于直接使用当前数
Runtime: 2 ms, faster than 14.26% of Java online submissions for Maximum Subarray.
        Memory Usage: 41.5 MB, less than 5.83% of Java online submissions for Maximum Subarray.
        Next challenges:
*/

public class SolutionV3 {

    public int maxSubArray(int[] nums) {
        int sum, res;
        int[] dp = new int[nums.length];
        res = sum = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }
}
