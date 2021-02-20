package com.leetcode.dp.stock.q121;


/*
* 121. Best Time to Buy and Sell Stock
* 买股票一，一次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*
* */

import com.leetcode.common.array.ArrayTools;

import java.lang.reflect.Array;

/*
- dp(n)包含两个状态，求取[0]代表没持有股票，[1]代表持有股票情况下能得到的利润
    $$
    dp(n)[0] = max(dp(n-1)[0], dp(n-1)[1] + prices[n-1])//
    dp(n)[1] = max(dp(n-1)[1], - prices[n-1])
    $$
- 状态n
- 选择，当前两个状态从何处发展而来
- base，基值情况比较复杂，当n == 0时，代表没有一支股票，而dp[0][1]代表持有——这是不可能的。根据状态转移公式，这个需要定义为-INF，否则会出现错误。
* */
class SolutionV2 {
    private static int[][] memo;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        memo = new int[n+1][2];
        return dp(n, prices)[0];
    }

    private int[] dp(int n, int[] prices) {
        if (n == 0) {
            memo[n][1] = Integer.MIN_VALUE;
            return memo[n];
        }
        if(memo[n][0]!=0) return memo[n];
        memo[n][0] = Math.max(dp(n - 1, prices)[0], dp(n - 1, prices)[1] + prices[n - 1]);
        memo[n][1] = Math.max(dp(n - 1, prices)[1],  - prices[n - 1]);
        return memo[n];
    }

    public static void main(String[] args) {
        int[] input = {7, 1, 5, 3, 6, 4};
        System.out.println(new SolutionV2().maxProfit(input));
        ArrayTools.disp2DArray(memo);

    }
}