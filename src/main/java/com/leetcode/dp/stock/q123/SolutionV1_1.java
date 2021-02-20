package com.leetcode.dp.stock.q123;


/*
迭代解法
Runtime: 96 ms, faster than 5.40% of Java online submissions for Best Time to Buy and Sell Stock III.
Memory Usage: 65.9 MB, less than 22.71% of Java online submissions for Best Time to Buy and Sell Stock III.
* */
public class SolutionV1_1 {

    public int maxProfit(int[] prices) {
        int n = prices.length, K = 2;
        int memo[][][] = new int[n + 1][K + 1][2];
        for (int i = 0; i <= n; i++) memo[i][0][1] = Integer.MIN_VALUE;
        for (int i = 0; i <= K; i++) memo[0][i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= K; k++) {
                memo[i][k][0] = Math.max(memo[i - 1][k][0], memo[i - 1][k][1] + prices[i-1]);
                memo[i][k][1] = Math.max(memo[i - 1][k][1], memo[i - 1][k - 1][0] - prices[i-1]);
            }
            // memo[i][1][0] = Math.max(memo[i - 1][1][0], memo[i - 1][1][1] + prices[i-1]);
            // memo[i][1][1] = Math.max(memo[i - 1][1][1], memo[i - 1][0][0] - prices[i-1]);
            // memo[i][2][0] = Math.max(memo[i - 1][2][0], memo[i - 1][2][1] + prices[i-1]);
            // memo[i][2][1] = Math.max(memo[i - 1][2][1], memo[i - 1][1][0] - prices[i-1]);
        }


        return memo[n][K][0];

    }
}
