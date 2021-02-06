package com.leetcode.dp.backpack.q494;

/*
 * 494. Target Sum
 * 达到目标和的可能性
 * https://leetcode.com/problems/target-sum/
 *
 * */


/*
备忘录，hash，效率比较低
Runtime: 106 ms, faster than 46.45% of Java online submissions for Target Sum.
Memory Usage: 39.7 MB, less than 18.14% of Java online submissions for Target Sum.
*/

/*
一种更像回溯的暴力动态规划
dp(i, sum)的定义是，i到N的元素加起来为sum的可能性之和
dp函数，对应加当前元素或者减去，等于两个子问题的可能性之和
    dp(i, sum) = dp(i+1, sum-cur) + dp(i+1, sum+cur)
选择，当i元素+或-，影响子问题目标sum值
base，i==N时，没有可选元素，如果sum==0，则有一种可能，如果sum！=0，则返回0

由于target可能为负数，所以并不好直接使用数组做memo
- 可以对target做映射，因为整体和不超过1000，但该方法比较繁琐
- 另一种方法是使用hash做memo，效率较低
Runtime: 106 ms
        Memory Usage: 39.7 MB
*/

import java.util.HashMap;
import java.util.Map;

class SolutionV2 {
    private Map<String, Integer> memo;
    private int BASE = 1000;
    // private
    public int findTargetSumWays(int[] nums, int S) {
        memo = new HashMap<>();

        return dp(0, S, nums);
    }

    private int dp(int i, int sum, int[] nums) {
        if (i == nums.length) return sum == 0 ? 1 : 0;
        String key = i + "," + sum;
        if (memo.containsKey(key)) return memo.get(key);
        int val = dp(i + 1, sum + nums[i], nums) + dp(i + 1, sum - nums[i], nums);
        memo.put(key, val);
        return val;
    }
}