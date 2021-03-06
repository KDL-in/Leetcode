package com.leetcode.comm.calculator.q224;
/*
 * 224. Basic Calculator
 * 基础计算器
 * https://leetcode.com/problems/basic-calculator/
 * */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
/*
* 224. Basic Calculator
* 计算器，含负数
* https://leetcode.com/problems/basic-calculator/
*
* */
/*
经典后缀表达式求法
错漏百出
* */
class Solution {

    public int calculate(String s) {

        Map<Character, Integer> prio = new HashMap<>();
        prio.put('$', 11);
        prio.put('(', 10);
        prio.put('#', -1);
        prio.put(')', 0);
        prio.put('+', 1);
        prio.put('-', 2);
        return postCal("$" + s + "#", prio);
    }

    private int postCal(String s, Map<Character, Integer> prio) {
        int i = 0, n = s.length(), t;
        char o;
        Stack<Character> op;
        Stack<Integer> num;
        op = new Stack();
        num = new Stack();
        op.push(s.charAt(i++));

        while (i < n) {
            o = s.charAt(i);
            if (o == ' ') {
                ++i;
                continue;
            }
            if (Character.isDigit(o)) {
                t = 0;
                do {
                    t = t * 10 + (o - '0');
                } while (Character.isDigit(o = s.charAt(++i)));
                num.push(t);
            }
            if (prio.get(o) <= prio.get(op.peek())) {
                if (op.peek() == '(') {
                    if (o == ')') {
                        op.pop();
                    } else op.push(o);
                    ++i;
                } else num.push(cal(num.pop(), num.pop(), op.pop()));
            } else {
                op.push(o);
                ++i;
            }
        }
        return num.isEmpty() ? 0 : num.peek();
    }


    private int cal(int b, int a, char o) {
        System.out.println(a + " " + o + " " + b);
        switch (o) {
            case '+':
                return a + b;
            case '-':
                return a - b;
        }
        return -1;
    }

    //
    public static void main(String[] args) {
        char arr[] = new char[256];
        System.out.println(arr['#']);
    }

}