package com.leetcode.datastructure.queuestack.q20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
* 20. Valid Parentheses
* 括号合法性
* https://leetcode.com/problems/valid-parentheses/
*
*
* */

/*
括号合法性检查，若非有三种括号，可以再O(1)的空间复杂度解决
Runtime: 1 ms, faster than 98.63% of Java online submissions for Valid Parentheses.
        Memory Usage: 36.9 MB, less than 93.47% of Java online submissions for Valid Parentheses.
*/

class Solution {
    public boolean isValid(String s) {
        int i; char c;
        Stack<Character> stack = new Stack<>();
        char[] map = new char[256];
        map['('] = ')';
        map['{'] = '}';
        map['['] = ']';
        for (i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map[c]!=0) {
                stack.push(map[c]);
                continue;
            }
            if (stack.isEmpty() || c != stack.peek()) break;
            stack.pop();
        }
        return i == s.length() && stack.isEmpty();
    }
}