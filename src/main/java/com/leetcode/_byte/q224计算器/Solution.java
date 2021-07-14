package com.leetcode._byte.q224计算器;

import java.util.Stack;

class Solution {
    public int calculate(String s) {
        char cur;
        int op = 1, ans = 0, num = 0;
        Stack<Integer> ops = new Stack<>();
        ops.push(op);
        for (int i = 0; i < s.length(); i++){
            cur = s.charAt(i);
            if (cur == ' ') continue;
            else if(Character.isDigit(cur)) num = num * 10 + (cur - '0');
            else {
                ans += num * op;
                num = 0;
                if (cur == '+') op = ops.peek();
                else if (cur == '-') op = -ops.peek();
                else if (cur == '(') ops.push(op);
                else ops.pop();
            }
        }
        return ans += num * op;
    }
}
