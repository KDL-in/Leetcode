package com.leetcode.math.prime.q204;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Arrays;

/*
 * 204. Count Primes
 * 素数筛选法
 * https://leetcode.com/problems/count-primes/
 *
 * */

/*
时间复杂度很难考虑
Runtime: 15 ms, faster than 55.43% of Java online submissions for Count Primes.
        Memory Usage: 37.8 MB, less than 32.36% of Java online submissions for Count Primes.
*/

class Solution {
    public int countPrimes(int n) {
        if (n < 2) return 0;
        int cur = 0, res = 0;
        boolean isPrime[] = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < n; i++) {
            if (isPrime[cur = i]) {
                while ((cur = cur + i) < n) isPrime[cur] = false;
                ++res;
            }
        }
        return res;
    }
}