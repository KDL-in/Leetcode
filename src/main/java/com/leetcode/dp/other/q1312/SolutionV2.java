package com.leetcode.dp.other.q1312;


/*
* 1312. Minimum Insertion Steps to Make a String Palindrome
* 最小步数回文串
* https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
*
* */



/*
迭代
- dp(i, j)定义为[i,j]转变为回文需要的最小变动步数，可以将dp(i,j)的子问题视为已经是回文，当s[i]!=s[j]时
    $$
    dp(i, j) = min(dp(i + 1, j), dp(i, j - 1)) + 1
    $$
    上述公式是该问题的关键。当s[i]=s[j]时，dp(i, j) = dp(i+1, j-1)。而当s[i]!=s[j]时，情况比较难考虑。例如
    会出现aaab，这种情况，它并不是直接dp(i+1, j-1)+2，而是1。所以，该递推式的是将这个问题委托给两个子问题dp(i + 1, j), dp(i, j - 1)
- 状态i，j
- 选择，选择哪个子问题
- base，当j<=i时，说明此时没有任何字母，return 0
Runtime: 14 ms, faster than 93.75% of Java online submissions for Minimum Insertion Steps to Make a String Palindrome.
Memory Usage: 40.4 MB, less than 47.96% of Java online submissions for Minimum Insertion Steps to Make a
* */

class SolutionV2 {
    public int minInsertions(String s) {
        int[][] memo;
        int n = s.length();
        memo = new int[n][n];
        for (int i = n-2; i >= 0; --i) {
            for (int j = i+1; j < n; j++) {
                memo[i][j] = s.charAt(i) == s.charAt(j) ? memo[i+1][j-1] :
                        Math.min(memo[i+1][j], memo[i][j-1]) + 1;
            }
        }
        return memo[0][n-1];
    }

}