package com.leetcode.dp.stock.q309;

import com.leetcode.common.array.ArrayTools;

/*
* 309. Best Time to Buy and Sell Stock with Cooldown
* 买股票，不限次数，冷冻期
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
* */
/*
q122拓展
- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], dp(n-2)[0] - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n = 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
当n = -1时，与n=0作一块处理即可。因为主要是用到了dp[1][1]计算第一天，持有股票的可能，由于第一次买不可能有冷冻期，所以想n=0一样返回0即可
Runtime: 1 ms, faster than 58.45% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
        Memory Usage: 39 MB, less than 14.53% of Java online submissions for Best Time to Buy and Sell Stock with
*/

class Solution {
    private static int[][] memo;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n + 1][2];
        return dp(n, prices)[0];
    }

    private int[] dp(int n, int[] prices) {
        if (n <= 0) {
            memo[0][1] = Integer.MIN_VALUE;
            return memo[0];
        }
        if(memo[n][0]!=0) return memo[n];
        memo[n][0] = Math.max(dp(n - 1, prices)[0], dp(n - 1, prices)[1] + prices[n-1]);
        memo[n][1] = Math.max(dp(n - 1, prices)[1],  dp(n-2,prices)[0] - prices[n - 1]);

        return memo[n];
    }

    public static void main(String[] args) {
        int[] input = {1,2,3,0,2};
        new Solution().maxProfit(input);
        ArrayTools.disp2DArray(memo);
    }
}