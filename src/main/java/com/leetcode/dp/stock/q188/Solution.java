package com.leetcode.dp.stock.q188;
/*
* 188. Best Time to Buy and Sell Stock IV
* 购买股票四，k次购买
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
*
* */

/*

Runtime: 7 ms, faster than 35.77% of Java online submissions for Best Time to Buy and Sell Stock IV.
        Memory Usage: 41.8 MB, less than 11.50% of Java online submissions for Best Time to Buy and Sell Stock IV.
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length, K = k;
        int memo[][][] = new int[n + 1][K + 1][2];
        for (int i = 0; i <= n; i++) memo[i][0][1] = Integer.MIN_VALUE;
        for (int i = 0; i <= K; i++) memo[0][i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for ( k = 1; k <= K; k++) {
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