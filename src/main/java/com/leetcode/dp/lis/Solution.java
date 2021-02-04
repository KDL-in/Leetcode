package com.leetcode.dp.lis.q300;

import java.util.Arrays;

/*
* 300. Longest Increasing Subsequence
* 最长递增子序列
* https://leetcode.com/problems/longest-increasing-subsequence/
* */
/*
动态规划
dp函数还是很难想到正确的形式。这道题从子集的角度考虑很难进一步得到动态规划的算法
重复子问题很难找。
dp[i]正确的定义是，以i为结尾的最长序列，非常精彩
base： 当nums[i]前面比其他值大的时候，说明它无法接前面任意子序列的前面
状态：nums[i]接上去后得到多长的lis
选择：前面任意子序列，选哪个接上
dp函数：前面也说了dp[i]
Runtime: 60 ms, faster than 42.45% of Java online submissions for Longest Increasing Subsequence.
        Memory Usage: 39 MB, less than 33.08% of Java online submissions for Longest Increasing Subsequence.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int res;
        int lis[] = new int[nums.length];
        res = 1;
        Arrays.fill(lis, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]  > nums[j]) lis[i] = Math.max(lis[i], lis[j] + 1);
            }
            res = Math.max(res, lis[i]);
        }
        return res;
    }
}