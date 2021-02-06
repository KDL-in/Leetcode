package com.leetcode.dp.subseq.match.q712;

/*
* 712. 两个字符串的最小ASCII删除和
* https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/
* */
/*
执行用时：39 ms, 在所有 Java 提交中击败了23.21%的用户
内存消耗：39.3 MB, 在所有 Java 提交中击败了18.73%的用户
* */

class Solution {
    private int[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        memo = new int[n1+1][n2+1];
        for (int i = 1; i <= n1; i++) memo[i][0] = s1.charAt(i-1) + memo[i - 1][0];
        for (int i = 1; i <= n2; i++) memo[0][i] = s2.charAt(i-1) + memo[0][i - 1];
        return dp(s1.length(), s2.length(), s1, s2);
    }

    private int dp(int i, int j, String s1, String s2) {
        if (i == 0 || j == 0 || memo[i][j] != 0) return memo[i][j];
        memo[i][j] = Math.min(dp(i - 1, j, s1, s2) + s1.charAt(i-1), dp(i, j - 1, s1, s2) + s2.charAt(j-1));
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
            // 这一步其实是多余的
            // dp[i-1][j-1]的值绝对比dp[i-1][j], dp[i][j-1]要更小
            memo[i][j] = Math.min(dp(i - 1, j - 1, s1, s2), memo[i][j]);
        }
        return memo[i][j];
    }
}