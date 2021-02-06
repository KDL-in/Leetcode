package com.leetcode.dp.subseq.lis.q300;

/*
* 300. Longest Increasing Subsequence
* 最长递增子序列
* https://leetcode.com/problems/longest-increasing-subsequence/
* */
/*

Runtime: 60 ms, faster than 42.45% of Java online submissions for Longest Increasing Subsequence.
        Memory Usage: 39 MB, less than 33.08% of Java online submissions for Longest Increasing Subsequence.
*/

class SolutionV1_1 {
    public int lengthOfLIS(int[] nums) {
        int res;
        int lis[] = new int[nums.length];
        res = lis[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i]  > nums[j]) lis[i] = Math.max(lis[i], lis[j] + 1);
            }
            res = Math.max(res, lis[i]);
        }
        return res;
    }
}