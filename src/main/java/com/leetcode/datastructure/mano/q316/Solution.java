package com.leetcode.datastructure.mano.q316;

/*
 * 316. Remove Duplicate Letters
 * 数组去重，但需要最小字典序，并且保留原有相对位置。
 * https://leetcode.com/problems/remove-duplicate-letters/
 * */

import java.util.Stack;

/*
一道很难的去重题目，数组去重，需要保证三件事。
1. 去重，由hash桶inStack保证
2. 原有相对顺序，由stack保证
3. 最小字典序
    这个很难。考虑当前加入字符，如果小于栈中的前一个字符，而该字符在后面存在，则必定可以替换前一个字符为当前字符
    因为，不管后面是什么顺序，当前字符必定优于前一个字符。这是一种比较隐晦的贪心策略。
    后面字符是否还存在，用count来判断，并使用单调栈的算法来。
时间复杂度，O（N），因为所有元素只会入栈一次，出栈一次
空间复杂度，O(K) + O(N)，O(N)为单调栈最大开销，K为字符hash桶开销。
Runtime: 3 ms, faster than 77.64% of Java online submissions for Remove Duplicate Letters.
Memory Usage: 38.7 MB, less than 83.65% of Java online submissions for Remove Duplicate Letters.
*/

class Solution {
    public String removeDuplicateLetters(String s) {
        char c;int count[];boolean[] inStack;StringBuilder builder;
        count = new int['z' + 1];
        inStack = new boolean['z' + 1];
        Stack<Character> stack = new Stack<>();
        builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) ++count[s.charAt(i)];
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            --count[c];
            if (inStack[c]) continue;
            while (!stack.isEmpty() && stack.peek() > c) {
                if (count[stack.peek()] == 0) break;
                inStack[stack.pop()] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }
        while (!stack.isEmpty()) builder.append(stack.pop());
        return "".equals(s) ? "" :builder.reverse().toString();
    }
}