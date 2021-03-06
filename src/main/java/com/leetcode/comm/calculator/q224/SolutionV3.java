package com.leetcode.comm.calculator.q224;

import jdk.nashorn.internal.ir.IfNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/*
思路：
将所有减操作都变成+(-x)
太恶心了，思路又错，考虑少-(-6)这种情况，无法修正
* */
public class SolutionV3 {
    public int calculate(String s) {
        int num = 0, n = s.length(), sign = 1, t = 0;
        char o;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            o = s.charAt(i);
            if (o == ' ') continue;
            else if (Character.isDigit(o)) num = num * 10 + (o - '0');
            else if (o == '(') stack.push((int) o);
            else if (o == '+' || o == '-') {
                stack.push(num * sign);
                num = 0;
                sign = o == '+' ? 1 : -1;
            } else if (o == ')') {
                t = 0;
                while (stack.peek() != '(') t += stack.pop();
                stack.pop();
                stack.push(t);
            }
        }
        t = num;
        while (!stack.isEmpty()) {
            t += stack.pop();
        }
        return t;
    }
}
