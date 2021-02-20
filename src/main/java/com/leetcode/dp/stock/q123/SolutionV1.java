package com.leetcode.dp.stock.q123;


/*
* 123. Best Time to Buy and Sell Stock III
* 买股票三，买三次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
*
* */

import com.leetcode.common.array.ArrayTools;

/*
Time Limit Exceeded

- dp(n, k)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润, n代表天数，k代表可交易的次数
    $$
    dp(n, k)[0] = max(dp(n-1, k)[0], dp(n-1, k)[1] + prices[n-1])//
    dp(n, k)[1] = max(dp(n-1, k)[1], dp(n-1, k-1)[0] - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
* */
class SolutionV1 {
    private static int[][][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n+1][2+1][2];
        return dp(n, 2, prices)[0];
    }

    private int[] dp(int n, int k, int[] prices) {
        if (n == 0 || k == 0) {
            memo[n][k][1] = Integer.MIN_VALUE;
            return memo[n][k];
        }
        if(memo[n][k][0]!=0) return memo[n][k];
        memo[n][k][0] = Math.max(dp(n - 1, k, prices)[0], dp(n - 1, k, prices)[1] + prices[n - 1]);
        memo[n][k][1] = Math.max(dp(n - 1, k, prices)[1],  dp(n - 1, k - 1, prices)[0] - prices[n - 1]);
        return memo[n][k];
    }
}