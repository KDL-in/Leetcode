package com.leetcode.math.bit.q191;
/*
* 191. Number of 1 Bits
* 1的个数，汉明重量
* https://leetcode.com/problems/number-of-1-bits/
*
* */

/*
位运算技巧，n&(n-1)必定能消除最后的1，包括符号位
Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.
Memory Usage: 35.7 MB, less than 83.47% of Java online submissions for Number of 1 Bits.
* */
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            ++res;
        }
        return res;
    }
}
