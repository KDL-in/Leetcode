package com.leetcode.dp.stock.q309;
/*
迭代，状态压缩
Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
Memory Usage: 36.8 MB, less than 75.51% of Java online submissions for Best Time to Buy and Sell Stock with
* */
public class SolutionV1_1 {
    public int maxProfit(int[] prices) {
        int n = prices.length, dp_i_0, dp_i_1,dp_ip2_0, t;
        dp_i_0 = dp_ip2_0 = 0;dp_i_1 = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i - 1]);
            dp_i_1 = Math.max(dp_i_1, dp_ip2_0 - prices[i - 1]);
            dp_ip2_0 = t;
        }
        return dp_i_0;
    }
}
