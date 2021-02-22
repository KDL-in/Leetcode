package com.leetcode.dp.other.q887;


/*
递归，备忘录
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
Memory Usage: 39.8 MB, less than 38.47% of Java online submissions for Super Egg Drop.
*/

public class SolutionV2_1 {
    private int[][] memo;

    public int superEggDrop(int K, int N) {
        memo = new int[K+1][N+1];
        int m = 1;
        while (dp(K, m) < N + 1) ++m;
        return m;
    }

    private int dp(int k, int m) {
        if (k == 1 || m == 1) return m + 1;
        if (memo[k][m]!=0) return memo[k][m];
        memo[k][m] = dp(k - 1, m - 1) + dp(k, m - 1);
        return memo[k][m];
    }
}
