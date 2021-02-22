package com.leetcode.dp.other.q10.error;

class Solution {
    private boolean [][] memo, flag;
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        memo = new boolean[n + 1][m + 1];  flag = new boolean[n+1][m+1];
        return reg(n, m, s, p);
    }

    private boolean reg(int i, int j, String s, String p) {
        if (i == 0 && j == 0) return true;
        if (i == 0 || j == 0) return false;
        char c;

        if ((c = p.charAt(j)) == '*') {
            memo[i][j] = reg(i, j - 1, s, p) || reg(i - 1, j - 1, s, p) || reg(i - 1, j, s, p);
        } else if (c == '.' || c == s.charAt(i)) {
            memo[i][j] = reg(i - 1, j - 1, s, p);
        } else {
            memo[i][j] = false;
        }
        return memo[i][j];
    }
}