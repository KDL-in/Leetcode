package com.leetcode.dp.subseq.match.q1143;


/*
优化的迭代动态规划
空间复杂度可以为O(min(N,M))
Runtime: 12 ms, faster than 42.76% of Java online submissions for Longest Common Subsequence.
Memory Usage: 37 MB, less than 97.00% of Java online submissions for Longest Common Subsequence.
*/

public class SolutionV2_1 {
    public int longestCommonSubsequence(String text1, String text2) {

        int n1 = text1.length(), n2 = text2.length(), pre, t;
        int[] dp = new int[n1 + 1];
        if (n1 == 0 || n2 == 0) return 0;
        for (int j = 1; j <= n2; j++) {
            pre = 0;
            for (int i = 1; i <= n1; i++) {
                t = text1.charAt(i-1) == text2.charAt(j-1) ? pre + 1 : Math.max(dp[i], dp[i-1]);
                pre = dp[i];
                dp[i] = t;
            }
        }
        return dp[n1];
    }
}
/*


0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 0 0 0 1 1 1 1 1
0 0 1 1 1 1 1 1 1 1
*/
