package com.leetcode.dp.subseq.match.q583;

/*
* 583. Delete Operation for Two Strings
* 序列匹配，删除以相同
* https://leetcode.com/problems/delete-operation-for-two-strings/
*
* */

/*
动态规划，memo递归
状态，dp(i,j)表示从s1[0:i]和s2[0:j]最大需要的步数
选择，i，j对应的字母是否相等，不相等则删除其一，i-1或者j-1
base，当i和j其中为-1的时候，表示一个字符串已经为“”，则此时之只要把另外一个全部字母删掉即可
dp函数，同状态，则memo[i][j] = s1.charAt(i) == s2.charAt(j) ? dp(i - 1, j - 1, s1, s2): Math.min(dp(i - 1, j, s1, s2), dp(i, j - 1, s1, s2)) +  + 1
Runtime: 16 ms, faster than 16.40% of Java online submissions for Delete Operation for Two Strings.
        Memory Usage: 43.7 MB, less than 9.15% of Java online submissions for Delete Operation for Two Strings.

*/

class Solution {
    private int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    private int dp(int i, int j, String s1, String s2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j]!=0) return memo[i][j];
        return memo[i][j] = s1.charAt(i) == s2.charAt(j) ? dp(i - 1, j - 1, s1, s2): Math.min(dp(i - 1, j, s1, s2), dp(i, j - 1, s1, s2)) +  + 1 ;
    }
}