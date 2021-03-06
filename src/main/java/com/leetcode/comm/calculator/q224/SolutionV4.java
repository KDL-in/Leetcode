package com.leetcode.comm.calculator.q224;

import java.util.Stack;
/*
终于对了…………核心思路是
- 边遍历边求和，负号则改变sign符号标志，读取随后的完整数字
- 遇到(，则先保存当前的res，和括号前的符号然后进入（）内计算括号内的子问题和，

* */
public class SolutionV4 {
    public int calculate(String s) {
        int n = s.length(), res = 0, sign = 1, tmp;
        char o;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            o = s.charAt(i);
            if (o == ' ') continue;
            else if (Character.isDigit(o)) {
                tmp = 0;
                while (i < n && Character.isDigit(o = s.charAt(i))) {
                    tmp = tmp * 10 + (o - '0');
                    ++i;
                }
                i--;
                res += tmp * sign;
            } else if (o == '+') sign = 1;
            else if (o == '-') sign = -1;
            else if (o == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (o == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SolutionV4().calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
