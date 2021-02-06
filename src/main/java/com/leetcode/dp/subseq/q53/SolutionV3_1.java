package com.leetcode.dp.subseq.q53;


/*
优化的动态规划，O(N), O(1)

Runtime: 1 ms, faster than 53.41% of Java online submissions for Maximum Subarray.
Memory Usage: 38.8 MB, less than 84.20% of Java online submissions for Maximum Subarray.*/

public class SolutionV3_1 {

    public int maxSubArray(int[] nums) {
        int sum, res, pre;
        pre = res = sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum  = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, sum);
            pre = sum;
        }
        return res;
    }

}
