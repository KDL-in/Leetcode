package com.leetcode.datastructure.queuestack.q225;

import java.util.LinkedList;
import java.util.Queue;

/*
* 225. Implement Stack using Queues
* 队列模拟栈
* https://leetcode.com/problems/implement-stack-using-queues/
*
* */

/*
因为队列的特性——什么顺序进什么顺序出，而目标模拟栈又是反序，所以并没有办法通过腾挪改变顺序
所以pop和push操作，必定有一个是O(N)无法避免
不过单个队列即可以实现栈，这个比较巧妙就是。
Runtime: 0 ms, faster than 100.00% of Java online submissions for Implement Stack using Queues.
        Memory Usage: 36.5 MB, less than 91.43% of Java online submissions for Implement Stack using Queues.
*/

class MyStack {

    private Queue<Integer> q;
    private int top_e;
    private int size;

    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
        size++;
        top_e = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int n = size;
        while (n > 1) {
            top_e = q.poll();
            q.offer(top_e);
            n--;
        }
        size--;
        return q.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return top_e;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}
