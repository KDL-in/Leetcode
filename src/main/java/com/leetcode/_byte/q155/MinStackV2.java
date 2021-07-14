package com.leetcode._byte.q155;

import java.util.Stack;

/*
 * 155. Min Stack
 * 最小栈
 * https://leetcode.com/problems/min-stack/
 * */
/*
经典题，push一个哨兵会简单很多。
* */

public class MinStackV2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStackV2() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);

        minStack.push(Math.min(x, minStack.peek()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
