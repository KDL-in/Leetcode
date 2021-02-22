package com.leetcode.dp.other.q887;
/*
* 887. Super Egg Drop
* 高楼丢鸡蛋
* https://leetcode.com/problems/super-egg-drop/
* */
/*

Runtime: 29 ms, faster than 36.04% of Java online submissions for Super Egg Drop.
Memory Usage: 39.4 MB, less than 60.06% of Java online submissions for Super Egg Drop.

*/

class SolutionV1_1 {
    private int[][] memo;
    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (k == 1) return n;
        if (n == 0) return 0;
        if (memo[k][n]!=0) return memo[k][n];

        int l,r, mid;
        l = 1; r = n+1;
        while (l < r) {
            mid = (l + r) >> 1;
            if ((dp(k-1, mid - 1)) < (dp(k, n - mid)))  l = mid + 1;
            else r = mid;
        }

        memo[k][n] = 1 + Math.max(dp(k - 1, l - 1), dp(k, n - r));
        // memo[k][n] = 1 + Math.max(t1, t2);
        return memo[k][n];
    }

    public static void main(String[] args) {
        System.out.println(new SolutionV1_1().superEggDrop(2, 6));
    }
}