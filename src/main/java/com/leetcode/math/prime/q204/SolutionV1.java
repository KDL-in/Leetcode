package com.leetcode.math.prime.q204;

import java.util.Arrays;

/*
 * 204. Count Primes
 * 素数筛选法
 * https://leetcode.com/problems/count-primes/
 *
 * */
/*
时间复杂度，由于每个n都需要访问一次，因此O(N)，空间复杂度O(N)
* */
/*
素数筛选法效率版本
Runtime: 12 ms, faster than 88.99% of Java online submissions for Count Primes.
Memory Usage: 37.6 MB, less than 46.00% of Java online submissions for Count Primes.
*/

class SolutionV1 {
    public int countPrimes(int n) {
        int res = 0;
        boolean isPrime[] = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)isPrime[j] = false;
        }
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) ++res;
        }
        return res;
    }

    public static void main(String[] args) {
        new SolutionV1().countPrimes(10);
    }
}