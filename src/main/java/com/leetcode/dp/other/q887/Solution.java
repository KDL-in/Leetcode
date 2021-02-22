package com.leetcode.dp.other.q887;
/*
* 887. Super Egg Drop
* 高楼丢鸡蛋
* https://leetcode.com/problems/super-egg-drop/
* */
/*
Time Limit Exceeded


*/

class Solution {
    private int[][] memo;
    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;
        if (memo[k][n]!=0) return memo[k][n];
        memo[k][n] = n;
        for (int i = 1; i <= n; i++)
            memo[k][n] = Math.min(memo[k][n], Math.max(dp(k - 1, i - 1), dp(k, n - i)) + 1);
        return memo[k][n];
    }
}