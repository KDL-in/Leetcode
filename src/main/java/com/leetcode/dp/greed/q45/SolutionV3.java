package com.leetcode.dp.greed.q45;

/*

Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game II.
Memory Usage: 41.5 MB, less than 26.16% of Java online submissions for Jump Game II.
* */

public class SolutionV3 {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
