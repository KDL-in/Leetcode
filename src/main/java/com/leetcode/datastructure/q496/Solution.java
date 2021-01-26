package com.leetcode.datastructure.q496;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
* 496. Next Greater Element I
* 单调栈，下一个更大的数
* https://leetcode.com/problems/next-greater-element-i/
* */


/*
关键的单调栈的使用，适用于身高排队的场景
Runtime: 3 ms, faster than 81.54% of Java online submissions for Next Greater Element I.
        Memory Usage: 39 MB, less than 87.22% of Java online submissions for Next Greater Element I.
*/

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s; int [] res; Map<Integer, Integer> map;
        s = new Stack<>(); res = new int[nums1.length]; map = new HashMap<>(nums2.length);
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums2[i]) s.pop();
            map.put(nums2[i], s.isEmpty() ? -1 : s.peek());
            s.push(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}