package com.leetcode.dp.stock.q714;

/*
* 714. Best Time to Buy and Sell Stock with Transaction Fee
* 买股票，多次购买，交易费
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
* */
/*
q122拓展
- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], dp(n-1)[0] - prices[n-1] - fee)
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。

Runtime: 3 ms, faster than 93.90% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
        Memory Usage: 48.2 MB, less than 68.41% of Java online submissions for Best Time to Buy and Sell Stock with
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length, dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE,t;
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i-1]);
            dp_i_1 = Math.max(dp_i_1, t - prices[i - 1] - fee);
        }
        return dp_i_0;
    }
}