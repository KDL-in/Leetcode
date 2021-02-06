package com.leetcode.dp.backpack.q494;

import com.leetcode.common.array.ArrayTools;

/*
状态压缩
背包问题常用，因为背包容量通常很大，导致内存溢出，所以通过状态压缩压缩成O(N）的空间复杂度。
Runtime: 2 ms, faster than 95.78% of Java online submissions for Target Sum.
        Memory Usage: 36.4 MB, less than 92.96% of Java online submissions for Target Sum.
*/

public class SolutionV3_2 {

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length, S = 0; int[] memo;

        for (int x : nums)
            S += x;
        if (S < target || ((S + target) & 1)==1) return 0;

        S = (S + target) >> 1;

        memo = new int[S+1];
        memo[0] = 1;

        for (int i = 1; i <= n; i++)
            for (int sum = S; sum >= nums[i-1]; sum--)
                memo[sum] = memo[sum] + memo[sum - nums[i-1]];

        return memo[S];
    }

}
