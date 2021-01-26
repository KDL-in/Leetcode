package com.leetcode.datastructure.q503;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
* 503. Next Greater Element II
* 单调栈，寻找下一个更大数
* https://leetcode.com/problems/next-greater-element-ii/
* */

/*
相当于在nums拼接nums上重复单调栈的算法
Runtime: 8 ms, faster than 78.74% of Java online submissions for Next Greater Element II.
        Memory Usage: 40.9 MB, less than 48.41% of Java online submissions for Next Greater Element II.
        Next challenges:
*/

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> s;  int [] res;
        s = new Stack<>();  res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums[i]) s.pop();
            s.push(nums[i]);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums[i]) s.pop();
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }

        return res;
    }
}