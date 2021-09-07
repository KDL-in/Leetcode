package com.leetcode.datastructure.queuestack.calculator.q227;//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
// 整数除法仅保留整数部分。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 2³¹ - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
// 
// 
// Related Topics 栈 数学 字符串 👍 449 👎 0


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionV2 {
    public int calculate(String s) {
        Stack<Character> op = new Stack<>();
        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                int cur = 0;
                while (i < s.length() && Character.isDigit((c=s.charAt(i)))) {
                    cur = cur * 10 + (c - '0');
                    i++;
                }
                num.push(cur);
                continue;
            }
            if (op.isEmpty()){
                op.push(c);
                i++;
            }
            else if ((c == '*' || c == '/') &&(op.peek() == '+' || op.peek() == '-')){
                op.push(c);
                i++;
            } else {
                num.push(cal(num.pop(), num.pop(), op.pop()));
            }

        }
        while (!op.isEmpty()) {
           num.push(cal(num.pop(), num.pop(), op.pop()));
        }
        return num.pop();
    }

    private int cal(int b, int a, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
class Solution {
    public int calculate(String s) {
        int ans = 0, num = 0;
        char preSign = '+';
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && c != ' ' || i == (s.length() - 1)) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                preSign = c;
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
