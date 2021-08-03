package com.leetcode.math.bit.q338;

/**
 * 位运算 + 动态规划 = 好家伙
 * 偶数的位数必定等于n/2
 * 奇数的位数必定等于偶数+1
 */
public class Solution2 {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i >> 1] + res[i & 1];
        }
        return res;
    }
}
