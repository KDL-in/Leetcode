package com.leetcode.dp.subseq.match.q1143;


/*
迭代实现，动态规划
Runtime: 11 ms, faster than 46.66% of Java online submissions for Longest Common Subsequence.
        Memory Usage: 43 MB, less than 44.07% of Java online submissions for Longest Common Subsequence.
*/

import com.leetcode.common.array.ArrayTools;

public class SolutionV2 {
    private static int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        if (n1 == 0 || n2 == 0) return 0;
        memo = new int[n1+1][n2+1];
        for (int j = 1; j <= n2; j++) {
            for (int i = 1; i <= n1; i++) {
                memo[i][j] = text1.charAt(i-1) == text2.charAt(j-1) ? memo[i - 1][j - 1] + 1 : Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }
        return memo[n1][n2];
    }

    public static void main(String[] args) {
        new SolutionV2().longestCommonSubsequence(
                "bsbininm",
                "jmjkbkjkv");
        ArrayTools.disp2DArray(memo);

    }
}
