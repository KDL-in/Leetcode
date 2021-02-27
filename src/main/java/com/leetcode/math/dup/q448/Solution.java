package com.leetcode.math.dup.q448;

import java.util.ArrayList;
import java.util.List;
/*
* 448. Find All Numbers Disappeared in an Array
* 找到未出现的元素
* https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
*
* */

/*
像这类题目，暴力解法有排序或hash（桶），但是时间复杂度和空间复杂度无法兼顾。

破局就在一个条件，n长的数组里给定的元素为1到n中的元素。于是可以通过索引来做一些文章，
求和，减法，抑或，负数等都是可以考虑的操作之一
Runtime: 4 ms
Memory Usage: 47.9 MB
* */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List res = new ArrayList<>();
        int t;
        for (int i = 0; i < nums.length; i++) {
            t = Math.abs(nums[i]);
            nums[t - 1] = -Math.abs(nums[t - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}