package com.leetcode.math.dup.q645;
/*
 * 645. Set Mismatch
 * 找到重复和缺失
 * https://leetcode.com/problems/set-mismatch/
 * */

/*
像这类题目，暴力解法有排序或hash（桶），但是时间复杂度和空间复杂度无法兼顾。

破局就在一个条件，n长的数组里给定的元素为1到n中的元素。于是可以通过索引来做一些文章，
求和，减法，抑或，负数等都是可以考虑的操作之一
Runtime: 2 ms, faster than 86.18% of Java online submissions for Set Mismatch.
Memory Usage: 40.7 MB, less than 51.04% of Java online submissions for Set Mismatch.
* */
class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup=0, miss=0;
        for (int i : nums) {
            i = i < 0 ? -i - 1 : i - 1;
            if (nums[i] > 0) nums[i] = -nums[i];
            else dup = i + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) miss = i + 1;
        }
        return new int[]{dup, miss};
    }
}