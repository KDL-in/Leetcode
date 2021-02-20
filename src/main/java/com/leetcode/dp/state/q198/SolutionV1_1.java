package com.leetcode.dp.state.q198;
/*
* 198. House Robber
* 偷东西
* https://leetcode.com/problems/house-robber/
* */
/*
有限状态机，状态压缩
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
Memory Usage: 38.2 MB, less than 13.08% of Java online submissions for House Robber.
*/

class SolutionV1_1 {

    public int rob(int[] nums) {
        int n = nums.length, dp_i_0 = 0, dp_i_1 = 0, t;
        int dp[][] = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1);
            dp_i_1 = t + nums[i - 1];
        }
        return Math.max(dp_i_0, dp_i_1);
    }

}