package com.leetcode.dp.backpack.q518;

/*
* 518. Coin Change 2
* 找零可能性，硬币无限
* https://leetcode.com/problems/coin-change-2/
* */
/*
完全背包问题
- dp(i, sum)，定义为set[:i]个物品下，得到sum的可能性
    dp[i, sum] = dp[i-1, sum] + dp[i, sum - coins[i-1]];
- 状态为i，sum
- 选择，当前i物品是否选用
- base，当sum = 0时，只能全不选，return 1， 当i = 0时，无物品，return 1
Runtime: 2 ms, faster than 100.00% of Java online submissions for Coin Change 2.
        Memory Usage: 36.1 MB, less than 95.99% of Java online submissions for Coin Change 2.

*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] memo = new int[amount + 1];
        memo[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int sum = coins[i-1]; sum <= amount; sum++) {
                memo[sum] = memo[sum] + memo[sum - coins[i-1]];
            }
        }
        return memo[amount];
    }
}