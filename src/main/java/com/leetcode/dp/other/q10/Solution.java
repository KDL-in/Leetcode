package com.leetcode.dp.other.q10;

import com.leetcode.common.array.ArrayTools;

/*
* 10. Regular Expression Matching
* 正则匹配 *的策略
* https://leetcode.com/problems/regular-expression-matching/
*
* */
/*
不是很好写的动态规划，基值条件和dp函数实现细节出错就没了。

字符串匹配，d(i, j)定义为s[:i], p[:j]是可以匹配
p[j] = '.'或s[i] = p[j]，则dp(i,j) = dp(i-1, j-1)
p[j] = '*'则共有两种情况
    零匹配，丢弃'x*'，dp(i,j) = dp(i, j-2)
    匹配正确，s缩小，p[j-1]=s[i] 或 p[j-1]='.'，则 dp(i, j) = dp(i-1,j)
base
dp(0,0) = true，当i=0，s为空，p[j] = '*'，则dp(0,j) = dp(0,j-2)

Runtime: 2 ms, faster than 90.30% of Java online submissions for Regular Expression Matching.
Memory Usage: 37.4 MB, less than 96.35% of Java online submissions for Regular Expression Matching.
*/
class Solution {
    private static boolean [][] memo;

    public boolean isMatch(String s, String p) {
        char c;
        int n = s.length(), m = p.length();
        memo = new boolean[n + 1][m + 1];
        memo[0][0] = true;
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j-1) == '*') memo[0][j] = memo[0][j - 2];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((c = p.charAt(j-1)) == '*') {
                    memo[i][j] = ((p.charAt(j-2) == '.'||p.charAt(j-2) == s.charAt(i-1)) && memo[i-1][j]) || memo[i][j-2];
                } else if (c == '.' || c == s.charAt(i-1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                }
            }
        }
        return memo[n][m];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aa", "a*"));
        ArrayTools.disp2DArray(memo);
    }
}