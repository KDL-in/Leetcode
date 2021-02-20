package com.leetcode.dp.state.q213;
/*
* 213. House Robber II
* 偷东西二
* https://leetcode.com/problems/house-robber-ii/
*
* */

/*
原理同q198，但是由于收尾相连，收尾不能同时选择，此时有两种情况[0,n-1)，和[1,n)，选择最大的即可
Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber II.
        Memory Usage: 38.6 MB, less than 6.23% of Java online submissions for House Robber II.
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n==1) return nums[0];
        return Math.max(rob(1, n-1, nums), rob(2, n, nums));
    }

    private int rob(int i, int j, int[] nums) {
        int dp_i_0 = 0, dp_i_1 = 0, t;
        for (; i <= j; i++) {
            t = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1);
            dp_i_1 = t + nums[i - 1];
        }
        return Math.max(dp_i_0, dp_i_1);
    }
}