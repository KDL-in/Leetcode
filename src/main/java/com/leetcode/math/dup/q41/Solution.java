package com.leetcode.math.dup.q41;
/*
* 41. First Missing Positive
* 寻找缺失的第一个整数
* https://leetcode.com/problems/first-missing-positive/
*
* */


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
题目要求空间复杂度为O(1)时间复杂度为O(N)
题目限制了nums.length的长度，以下暴力解法勉勉强强。
Runtime: 1 ms, faster than 43.11% of Java online submissions for First Missing Positive.
        Memory Usage: 37.2 MB, less than 19.04% of Java online submissions for First Missing Positive.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        Set set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i < 302; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }
}