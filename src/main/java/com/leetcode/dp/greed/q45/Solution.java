package com.leetcode.dp.greed.q45;

/*
* 45. Jump Game II
* 跳跃游戏
* https://leetcode.com/problems/jump-game-ii/
* */

/*
超时，动态规划。
dp[i]定义为到达该点所需要的最小步数
    dp[i] = min(dp[i], dp[j] + 1), where 0 <= j < i and j + nums[j] >= i
    需要遍历之前的状态找到最优解
时间复杂度，O(N^2)，空间O(N)
* */
class Solution {
    public int jump(int[] nums) {
        int N = nums.length;
        int dp[] = new int[N];
        for (int i = 1; i < N; i++) {
            dp[i] = N;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[N-1];
    }
}