package com.leetcode.dp.stock.q121;


/*
* 121. Best Time to Buy and Sell Stock
* 买股票一，一次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*
* */

import com.leetcode.common.array.ArrayTools;

/*

- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
Runtime: 2 ms, faster than 50.63% of Java online submissions for Best Time to Buy and Sell Stock.
Memory Usage: 52 MB, less than 42.96% of Java online submissions for Best Time to Buy and Sell Stock.
* */
class SolutionV2_1 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length, state0 = 0, state1 = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            state0 = Math.max(state0, state1 + prices[i-1]);
            state1 = Math.max(state1, -prices[i - 1]);
        }
        return state0;
    }

}