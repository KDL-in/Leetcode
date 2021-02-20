package com.leetcode.dp.stock.q121;


/*
* 121. Best Time to Buy and Sell Stock
* 买股票一，一次
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
*
* */

/*
原理，高个子排队，one pass
Runtime: 1 ms, faster than 98.84% of Java online submissions for Best Time to Buy and Sell Stock.
Memory Usage: 52 MB, less than 47.42% of Java online submissions for Best Time to Buy and Sell Stock.
* */
class Solution {
    public int maxProfit(int[] prices) {
        int res, max;
        res = max = 0;
        for (int i = prices.length - 1; i >= 0; --i) {
            if (max > prices[i]) res = Math.max(res, max - prices[i]);
            else max = prices[i];
        }
        return res;
    }
}