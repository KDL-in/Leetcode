package com.leetcode.dp.other.q887;


/*
递归
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
        Memory Usage: 35.9 MB, less than 91.40% of Java online submissions for Super Egg Drop.
*/

public class SolutionV2 {
    public int superEggDrop(int K, int N) {
        int m = 1;
        while (dp(K, m) < N + 1) ++m;
        return m;
    }

    private int dp(int k, int m) {
        if (k == 1 || m == 1) return m + 1;
        return dp(k - 1, m - 1) + dp(k, m - 1);
    }
}
