package com.leetcode.dp.subseq.q516;


/*
 * 516. Longest Palindromic Subsequence
 * 最长回文字串
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * */

/*
动态规划迭代实现，对角线遍历
对角线遍历：
for (int k = 1; k < n; k++) {
    for (int i = 0; i < n - k; i++) {
        int j = i + k;
    }
}
倒上三角反向遍历
for (int i = n - 1; i >= 0; i--) {
    for (int j = i + 1; j < n; j++) {
    }
}
Runtime: 40 ms, faster than 58.14% of Java online submissions for Longest Palindromic Subsequence.
Memory Usage: 48.9 MB, less than 81.68% of Java online submissions for Longest Palindromic Subsequence.
 */

class SolutionV2 {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int k = 1; k < n; k++) {
            int m = n - k;
            for (int i = 0; i < m; i++) {
                int j = i + k;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] + 2 : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

}