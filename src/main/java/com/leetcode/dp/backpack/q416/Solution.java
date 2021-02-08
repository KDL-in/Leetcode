package com.leetcode.dp.backpack.q416;

/*
* 416. Partition Equal Subset Sum
* 子集目标和
* https://leetcode.com/problems/partition-equal-subset-sum/
*
* */

/*
子集背包问题，动态规划，压缩
需要简单转化一下，优化目标单一化，sum(A) = sum(set) / 2
- dp(i, sum)定义为set[:i]子集之和为sum的可能，true or false
    dp[i,sum] = dp[i-1, sum - nums[i - 1]] || dp[i-1, sum];
- 选择，当前i元素是否考虑，
- 状态，i和sum，影响可能性dp
- base，i==0时，集合没有元素，return 0，sum==0时，只有一种选择满足，那就是什么都不选， return 1
复杂度 O(N SUM) O(SUM)
Runtime: 22 ms, faster than 70.32% of Java online submissions for Partition Equal Subset Sum.
        Memory Usage: 38.6 MB, less than 81.21% of Java online submissions for Partition Equal Subset Sum.
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0, n = nums.length;
        for (int num : nums) s += num;
        if ((s & 1) ==1) return false;
        s >>= 1;
        boolean[] memo = new boolean[s + 1];
        memo[0] = true;
        for (int i = 1; i < n; i++) {
            for (int sum = s; sum >= nums[i-1]; sum--) {
                memo[sum] = memo[sum - nums[i - 1]] || memo[sum];
            }
        }
        return memo[s];
    }

    public static void main(String[] args) {
        int nums[] = {1,5,11,5};
        new Solution().canPartition(nums);
    }
}
