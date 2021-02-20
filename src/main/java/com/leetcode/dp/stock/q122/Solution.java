package com.leetcode.dp.stock.q122;

/*
* 122. Best Time to Buy and Sell Stock II
* 股票问题二，多次买卖
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
*
* */
/*
one pass, 降价之前卖出，降价的瞬间发生重新购入，
这个直觉是正确的，假设
$$
p[i] < p[i-1] < p[i + k]
$$
此时，以p[i-1]作为最高价格卖出，然后以p[i]购入。
因为
$$
p[i + k] - pre < (p[i+k] - p[i]) + (p[i-1] - pre) //
= (p[i+k] - pre) + (p[i-1] - p[i])
$$
Runtime: 1 ms, faster than 71.52% of Java online submissions for Best Time to Buy and Sell Stock II.
        Memory Usage: 38.8 MB, less than 43.47% of Java online submissions for Best Time to Buy and Sell Stock II.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n, prof, curProf, curCost;
        n = prices.length; prof = curProf = 0; curCost = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                curProf = prices[i] - curCost;
            } else {
                curCost = prices[i];
                prof += curProf;
                curProf = 0;
            }
        }
        return prof += curProf;
    }
}