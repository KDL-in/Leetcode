package com.leetcode.dp.stock.q123;


/*
迭代解法, 状态压缩
Runtime: 4 ms, faster than 67.78% of Java online submissions for Best Time to Buy and Sell Stock III.
Memory Usage: 55.5 MB, less than 36.15% of Java online submissions for Best Time to Buy and Sell Stock III.
* */
public class SolutionV1_2 {

    public int maxProfit(int[] prices) {
        int n, K, dp_i_1_0, dp_i_1_1, dp_i_2_0, dp_i_2_1, dp_ip_0_0, dp_ip_1_0;
        n = prices.length; K = 2; dp_i_1_0 = dp_i_2_0 = dp_ip_0_0 = dp_ip_1_0 = 0;dp_i_1_1 = dp_i_2_1 = Integer.MIN_VALUE;



        for (int i = 1; i <= n; i++) {
            dp_i_1_0 = Math.max(dp_i_1_0,dp_i_1_1 + prices[i-1]);
            dp_i_1_1 = Math.max(dp_i_1_1, dp_ip_0_0 - prices[i-1]);
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + prices[i-1]);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_ip_1_0 - prices[i-1]);
            dp_ip_1_0 = dp_i_1_0;
            // memo[i][1][0] = Math.max(memo[i - 1][1][0], memo[i - 1][1][1] + prices[i-1]);
            // memo[i][1][1] = Math.max(memo[i - 1][1][1], memo[i - 1][0][0] - prices[i-1]);
            // memo[i][2][0] = Math.max(memo[i - 1][2][0], memo[i - 1][2][1] + prices[i-1]);
            // memo[i][2][1] = Math.max(memo[i - 1][2][1], memo[i - 1][1][0] - prices[i-1]);


        }
        return dp_i_2_0;

    }
}
