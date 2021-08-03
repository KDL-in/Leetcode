package com.leetcode.math.bit.q338;

/**
 * 动态规划
 * 000  0
 * 001  1
 * ------
 * 010  2
 * 011  3   2 + 1
 * -----
 * 100  4
 * 101  5   4 + 1
 * 110  6   4 + 2
 * 111  7   4 + 3
 *
 */
public class Solution3 {

    public int[] countBits(int num) {
        int memo[] = new int[num + 1];
        for (int i = 1; i <= num; i *= 2) {
            memo[i] = 1;
            for (int j = 1; j < i; j++) {
                if (i + j > num) return memo;
                memo[i + j] = memo[i] + memo[j];

            }
        }
        return memo;
    }

}
