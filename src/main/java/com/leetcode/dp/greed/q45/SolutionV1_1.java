package com.leetcode.dp.greed.q45;

/*
* 45. Jump Game II
* 跳跃游戏
* https://leetcode.com/problems/jump-game-ii/
* */
class SolutionV1_1 {
    public int jump(int[] nums) {
        int N = nums.length;
        int dp[] = new int[N],maxJump[] = new int[N];
        for (int i = 0; i < N; i++) maxJump[i] = nums[i] + i;
        for (int i = 1; i < N; i++) {
            dp[i] = N;
            for (int j = 0; j < i; j++) {
                if (maxJump[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[N-1];
    }
}