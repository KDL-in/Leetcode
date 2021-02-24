package com.leetcode.math.fac.q793;
/*
* 793. Preimage Size of Factorial Zeroes Function
* 阶乘尾0进阶
* https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
*
* */
/*
超时
* */
class Solution {
    public int preimageSizeFZF(int K) {
        int res = 0, t;
        for (int i = 0; ; i++) {
            if ((t = trailingZeroes(i)) == K) ++res;
            else if (t>K) break;
        }
        return res;
    }

    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().preimageSizeFZF(80502705));
    }
}