package com.leetcode.recursion.q241求所有可能的运算结果_加括号;

import java.util.*;

/*
 * 241. Different Ways to Add Parentheses
 * 加括号运算
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * */

/*
求所有可能的运算结果-加括号
本题本质上是求所有运算优先级（加括号），不正确的考虑方式，这道题递归很难写。例如考虑运算符的全排列，考虑运算符消去，都特别难实现。

每一层，其实做的是(a) (b c d)，从某个运算符处分开，然后分别给两边加上括号。

本题严格意义上属于分治法——不断缩小问题规模。

Runtime: 1 ms, faster than 99.68% of Java online submissions for Different Ways to Add Parentheses.
Memory Usage: 38.9 MB, less than 64.82% of Java online submissions for Different Ways to Add Parentheses.
* */
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        return div(0, input.length() - 1, input);
    }

    private List<Integer> div(int i, int j, String input) {
        List<Integer> res = new ArrayList<>();

        for (int k = i + 1; k < j; ++k) {
            char op = input.charAt(k);
            if (op>='0'&&op<='9') continue;
            List<Integer> l = div(i, k - 1, input);
            List<Integer> r = div(k + 1, j, input);
            for (Integer a : l) {
                for (Integer b : r) {
                    res.add(cal(a, b, op));
                }
            }
        }
        if (res.isEmpty())res.add(Integer.parseInt(input.substring(i, j+1)));
        return res;
    }

    private Integer cal(Integer a, Integer b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default: return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().diffWaysToCompute("10+5"));
    }
}