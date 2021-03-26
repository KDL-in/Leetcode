package com.leetcode.comm.lcof.q31;
/*
946. Validate Stack Sequences
有效的栈弹出序列。
https://leetcode.com/problems/validate-stack-sequences/
* */
import java.util.Stack;

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int i = 0, j = 0, n = pushed.length, m = popped.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        while(i < n && j < m){
            while(stack.peek() != popped[j] && i < n)
                stack.push(pushed[i++]);
            while (j < m && stack.peek() == popped[j] ) {
                stack.pop();
                j ++;
            }
            //System.out.println)
        }
        return j == m;
    }
}