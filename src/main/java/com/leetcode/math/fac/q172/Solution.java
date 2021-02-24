package com.leetcode.math.fac.q172;

/*
* 172. Factorial Trailing Zeroes
* 阶乘尾0的个数
* https://leetcode.com/problems/factorial-trailing-zeroes/
* */
/*
得到一个0其实取决于因子2和5.下面是暴力解法。
事实上，2的因子总是多于5的因子，所以有多少对2和5取决于5的数量。

时间复杂度：

对于任意n，需要计算k次，5^k = n，因而k = log n，所以整体时间复杂度为O(N log N)
Runtime: 35 ms, faster than 5.10% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 36.1 MB, less than 38.90% of Java online submissions for Factorial Trailing Zeroes.
* */

class Solution {
    public int trailingZeroes(int n) {
        int two, five, cur;
        two = five = 0;
        for (int i = 1; i <= n; i++) {
            cur = i;
            while (cur % 5 == 0) {
                cur /= 5;
                ++five;
            }
            while (cur % 2 == 0) {
                cur /= 2;
                ++two;
            }
        }
        return Math.min(two, five);
    }
}