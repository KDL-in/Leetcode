package com.leetcode.dp.subseq.match.q1143;
/*
* 1143. Longest Common Subsequence
* 最长公共子序列
* https://leetcode.com/problems/longest-common-subsequence/
*
* */

/*
备忘录动态规划
时间空间都是o(N^2)
Runtime: 40 ms, faster than 7.66% of Java online submissions for Longest Common Subsequence.
        Memory Usage: 43.1 MB, less than 27.87% of Java online submissions for Longest Common Subsequence.
*/

class Solution {
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];
        return dp(text1.length() - 1, text2.length() - 1, text1, text2);
    }

    private int dp(int i, int j, String text1, String text2) {
        if (i==-1||j==-1) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = text1.charAt(i) == text2.charAt(j) ? 1 + dp(i - 1, j - 1, text1, text2) : Math.max(dp(i - 1, j, text1, text2), dp(i, j - 1, text1, text2));
        return memo[i][j];
    }
}