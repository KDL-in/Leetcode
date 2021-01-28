package com.leetcode.datastructure.queuestack.q232;

import java.util.Stack;
/*
*
* 232. Implement Queue using Stacks
* 两栈实现队列
* https://leetcode.com/problems/implement-queue-using-stacks/
 * */
/*
使用两个栈模拟，push的时候直接push到s1，pop的时候从s2 pop，若s2为空，则必须将s1中的元素push到s2中，顺序颠倒
Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement Queue using Stacks.
        Memory Usage: 36.8 MB, less than 75.13% of Java online submissions for Implement Queue using Stacks.
*/

class MyQueue {
    private Stack<Integer> s1, s2;
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.empty()) while (!s1.empty()) s2.push(s1.pop());
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (s2.empty()) while (!s1.empty()) s2.push(s1.pop());
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */