package com.leetcode.dp.subseq.q53;


/*
贪心，
时间复杂度O(N)，空间O(1)
若某时刻sum为负数，则该时刻之前的序列对结果没有贡献，可以舍去
Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
        Memory Usage: 39.4 MB, less than 18.35% of Java online submissions for Maximum Subarray.
*/

public class SolutionV2 {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = -Integer.MAX_VALUE;
        for (int x : nums) {
            sum += x;
            max = Math.max(sum, max);
            if (sum < 0) sum = 0;
        }
        return max;
    }
}
