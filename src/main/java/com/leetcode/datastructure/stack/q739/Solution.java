package com.leetcode.datastructure.stack.q739;

import java.util.Stack;

/*
* 739. Daily Temperatures
* 下一个更高的值的间隔
* https://leetcode.com/problems/daily-temperatures/
* */

/*
和单调栈的基本一样
只不过需要同时维护dayStack，用于记录tStack单调栈中被覆盖（出栈）的数字个数
Runtime: 22 ms, faster than 29.27% of Java online submissions for Daily Temperatures.
        Memory Usage: 47.2 MB, less than 69.89% of Java online submissions for Daily Temperatures.
*/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> tStack, dayStack;  int [] res; int d;
        tStack = new Stack<>(); dayStack = new Stack<>(); res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            d = 1;
            while (!tStack.isEmpty() && tStack.peek() <= T[i]){
                d += dayStack.peek();
                tStack.pop();
                dayStack.pop();
            }
            res[i] = tStack.isEmpty() ? 0 : d;
            tStack.push(T[i]);
            dayStack.push(d);
        }
        return res;
    }
}