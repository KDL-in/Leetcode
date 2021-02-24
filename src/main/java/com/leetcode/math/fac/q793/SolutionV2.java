package com.leetcode.math.fac.q793;
/*
二分搜索，因为K和f(x)是单调不减函数，所以可以应用二分搜索
Runtime: 0 ms, faster than 100.00% of Java online submissions for Preimage Size of Factorial Zeroes Function.
        Memory Usage: 35.8 MB, less than 46.34% of Java online submissions for Preimage Size of Factorial Zeroes Function.
*/

class SolutionV2 {
    public int preimageSizeFZF(int K) {
        int l, r, mid;
        l = 0; r = 5 * K + 1;
        while (l < r) {
            mid = (l + r) >> 1;
            if (f(mid) < K) l = mid + 1;
            else r = mid;
        }
        return f(l) > K ? 0 : 5;
    }

    public int f(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

}