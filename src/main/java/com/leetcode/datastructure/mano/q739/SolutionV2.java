package com.leetcode.datastructure.mano.q739;

import java.util.Stack;

/*
非常简洁的实现，巧用index将两个栈的信息融合再一起
https://leetcode.com/problems/daily-temperatures/solution/
Runtime: 17 ms, faster than 52.65% of Java online submissions for Daily Temperatures.
        Memory Usage: 47.1 MB, less than 79.92% of Java online submissions for Daily Temperatures.
*/

public class SolutionV2 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
}
