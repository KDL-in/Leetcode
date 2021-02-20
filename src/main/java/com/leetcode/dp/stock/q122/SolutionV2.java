package com.leetcode.dp.stock.q122;



/*

- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], dp(n-1)[0] - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
Runtime: 1 ms, faster than 71.52% of Java online submissions for Best Time to Buy and Sell Stock II.
Memory Usage: 39 MB, less than 27.09% of Java online submissions for Best Time to Buy and Sell Stock II.
* */
class SolutionV2 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length, state0 = 0, state1 = Integer.MIN_VALUE,t;
        for (int i = 1; i <= n; i++) {
            t = state0;
            state0 = Math.max(state0, state1 + prices[i-1]);
            state1 = Math.max(state1, t - prices[i - 1]);
        }
        return state0;
    }

}