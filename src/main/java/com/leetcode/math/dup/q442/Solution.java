package com.leetcode.math.dup.q442;

import java.util.ArrayList;
import java.util.List;
/*
* 442. Find All Duplicates in an Array
* 寻找所有重复的元素
* https://leetcode.com/problems/find-all-duplicates-in-an-array/
*
* */
/*
同理q448
Runtime: 5 ms, faster than 89.65% of Java online submissions for Find All Duplicates in an Array.
Memory Usage: 48.6 MB, less than 22.49% of Java online submissions for Find All Duplicates in an Array
* */

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List res = new ArrayList();
        for (int idx : nums) {
            idx = idx < 0 ? -idx - 1 : idx - 1;
            if (nums[idx] < 0) res.add(idx + 1);
            else nums[idx] = -nums[idx];
        }
        return res;
    }
}