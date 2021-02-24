package com.leetcode.math.bit.q231;
/*
* 231. Power of Two
* 2的次方数
* https://leetcode.com/problems/power-of-two/
*
*
* */

/*
负数不是2的次方，只有一个1
Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
        Memory Usage: 38.2 MB, less than 8.77% of Java online submissions for Power of Two.
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n-1)) == 0;
    }
}