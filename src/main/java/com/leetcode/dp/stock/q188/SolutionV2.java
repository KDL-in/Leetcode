package com.leetcode.dp.stock.q188;
/*
* 188. Best Time to Buy and Sell Stock IV
* 购买股票四，k次购买
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
*
* */
/*
状态压缩，通用形式
Runtime: 4 ms, faster than 82.54% of Java online submissions for Best Time to Buy and Sell Stock IV.
Memory Usage: 36.5 MB, less than 90.44% of Java online submissions for Best Time to Buy and Sell Stock IV.
* */
class SolutionV2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int memo[][] = new int[k + 1][2];
        for (int i = 0; i <= k; i++) memo[i][1] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                memo[j][0] = Math.max(memo[j][0], memo[j][1] + prices[i-1]);
                memo[j][1] = Math.max(memo[j][1], memo[j - 1][0] - prices[i-1]);
            }
            // memo[i][1][0] = Math.max(memo[i - 1][1][0], memo[i - 1][1][1] + prices[i-1]);
            // memo[i][1][1] = Math.max(memo[i - 1][1][1], memo[i - 1][0][0] - prices[i-1]);
            // memo[i][2][0] = Math.max(memo[i - 1][2][0], memo[i - 1][2][1] + prices[i-1]);
            // memo[i][2][1] = Math.max(memo[i - 1][2][1], memo[i - 1][1][0] - prices[i-1]);
        }


        return memo[k][0];
    }
}