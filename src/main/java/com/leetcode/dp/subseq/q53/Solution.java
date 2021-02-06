package com.leetcode.dp.subseq.q53;
/*
* 53. Maximum Subarray
*  最大子序列和
* https://leetcode.com/problems/maximum-subarray/
*
* */
/*
精巧的暴力，O(N^2)，空间复杂度O（1）
Runtime: 129 ms, faster than 5.01% of Java online submissions for Maximum Subarray.
        Memory Usage: 41.2 MB, less than 12.84% of Java online submissions for Maximum Subarray.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int max = -Integer.MAX_VALUE, sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}