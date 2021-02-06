package com.leetcode.dp.backpack.q494;

/*
 * 494. Target Sum
 * 达到目标和的可能性
 * https://leetcode.com/problems/target-sum/
 *
 * */


/*
一种更像回溯的暴力动态规划
dp(i, sum)的定义是，i到N的元素加起来为sum的可能性之和
dp函数，对应加当前元素或者减去，等于两个子问题的可能性之和
    dp(i, sum) = dp(i+1, sum-cur) + dp(i+1, sum+cur)
选择，当i元素+或-，影响子问题目标sum值
base，i==N时，没有可选元素，如果sum==0，则有一种可能，如果sum！=0，则返回0

Runtime: 470 ms, faster than 31.47% of Java online submissions for Target Sum.
Memory Usage: 36.3 MB, less than 96.46% of Java online submissions for Target Sum.
*/

class Solution {
    // private
    public int findTargetSumWays(int[] nums, int S) {
        return dp(0, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (i == nums.length) return sum == 0 ? 1 : 0;
        return dp(i + 1, sum + nums[i], nums) + dp(i + 1, sum - nums[i], nums);
    }
}