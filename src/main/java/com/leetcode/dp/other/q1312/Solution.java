package com.leetcode.dp.other.q1312;


/*
* 1312. Minimum Insertion Steps to Make a String Palindrome
* 最小步数回文串
* https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
*
* */



/*
- dp(i, j)定义为[i,j]转变为回文需要的最小变动步数，可以将dp(i,j)的子问题视为已经是回文，当s[i]!=s[j]时
    $$
    dp(i, j) = min(dp(i + 1, j), dp(i, j - 1)) + 1
    $$
    上述公式是该问题的关键。当s[i]=s[j]时，dp(i, j) = dp(i+1, j-1)。而当s[i]!=s[j]时，情况比较难考虑。例如
    会出现aaab，这种情况，它并不是直接dp(i+1, j-1)+2，而是1。所以，该递推式的是将这个问题委托给两个子问题dp(i + 1, j), dp(i, j - 1)
- 状态i，j
- 选择，选择哪个子问题
- base，当j<=i时，说明此时没有任何字母，return 0
Runtime: 12 ms, faster than 97.83% of Java online submissions for Minimum Insertion Steps to Make a String Palindrome.
Memory Usage: 39.9 MB, less than 89.13% of Java online submissions for Minimum Insertion Steps to Make a
* */

class Solution {
    private int[][] memo;
    public int minInsertions(String s) {
        int n = s.length();
        memo = new int[n][n];
        return dp(0, n - 1, s.toCharArray());
    }

    private int dp(int i, int j, char[] s) {
        if (j <= i) return 0;
        if (memo[i][j]!=0) return memo[i][j];
        memo[i][j] = s[i] == s[j] ? dp(i + 1, j - 1, s) :
                Math.min(dp(i + 1, j, s), dp(i, j - 1, s)) + 1;
        return memo[i][j];
    }
}