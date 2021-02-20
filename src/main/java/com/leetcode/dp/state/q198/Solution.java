package com.leetcode.dp.state.q198;
/*
* 198. House Robber
* 偷东西
* https://leetcode.com/problems/house-robber/
* */
/*
有限状态机，而且符合贪心选择原则。当前的两个状态只和上一个状态有关。
- dp(i)定义为[0:i]能够得到的最大值
    $$
    dp(i)[0] = max(dp(i-1)[0], dp(i-1)[1])
    dp(i)[1] = dp(i-1)[0] + nums[i-1]
    $$
- 状态i
- 选择，当前状态如何从上一级状态转化过来
- base，当i=0时，此时没有任何一家，因而return 0
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
        Memory Usage: 38.4 MB, less than 8.34% of Java online submissions for House Robber.
*/

class Solution {

    public int rob(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

}