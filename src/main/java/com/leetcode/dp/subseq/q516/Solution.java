package com.leetcode.dp.subseq.q516;


import com.leetcode.common.array.ArrayTools;

/*
* 516. Longest Palindromic Subsequence
* 最长回文子串
* https://leetcode.com/problems/longest-palindromic-subsequence/
*
* */

/*
动态规划，递归备忘录
dp定义，dp(i,j)表示s[i:j]的最长回文串的长度
    dp(i,j) = s[i] == s[j] ? (dp(i+1, j-1, s) + 2) : max(dp(i, j - 1, s), dp(i + 1, j, s))
状态，i，j。dp(i,j)表示s[i:j]的最长回文串的长度
选择，考虑引入s[i]和s[j]
base，当i=j的时候，这个时候只有一个字母，return 1，当j < i的时候，这时没有字母，return 0；
Runtime: 22 ms, faster than 95.08% of Java online submissions for Longest Palindromic Subsequence.
        Memory Usage: 48.9 MB, less than 86.80% of Java online submissions for Longest Palindromic Subsequence.
*/

class Solution {
    private int [][]memo;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new int[n][n];
        return dp(0, n-1, s);
    }

    private int dp(int i, int j, String s) {
        if (i == j) return 1;
        if (j < i) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] = s.charAt(i) == s.charAt(j) ? (dp(i+1, j-1, s) + 2) : Math.max(dp(i, j - 1, s), dp(i + 1, j, s));
        // System.out.println(i + " " + j + " " + memo[i][j]);
        return memo[i][j];
    }

}