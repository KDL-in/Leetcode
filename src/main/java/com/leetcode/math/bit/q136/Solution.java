package com.leetcode.math.bit.q136;
/*
* 136. Single Number
* 查找单独的数
* https://leetcode.com/problems/single-number/
* */



/*
抑或技巧，不同等于相加，相同等于相减
Runtime: 1 ms, faster than 96.29% of Java online submissions for Single Number.
Memory Usage: 47.5 MB, less than 11.27% of Java online submissions for Single Number.
* */
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }
}