package com.leetcode._byte.q394;

import java.util.Stack;

/*
 * 394. Decode String
 * 字符串解码
 * https://leetcode.com/problems/decode-string/
 *
 * */
/*
栈的使用
* */
class Solution {
    public String decodeString(String s) {
        int n = s.length(), time;
        StringBuilder cur = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String c;
        for (int i = 0; i < n; i++) {
            c = s.substring(i, i+1);
            if (!("]".equals(c))){
                stack.push(c);
                continue;
            }
            cur.setLength(0);
            while (!((c = stack.pop()).equals("["))){
                cur.append(c);
            }
            c = cur.toString();
            cur.setLength(0);
            while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))){
                cur.append(stack.pop());
            }
            time = Integer.parseInt(cur.reverse().toString());
            cur.setLength(0);
            for (int j = 0; j < time; j++) {
                cur.append(c);
            }
            stack.push(cur.toString());
            // System.out.println(stack.peek());
        }
        cur.setLength(0);
        while (!stack.isEmpty()) {
            cur.append(stack.pop());
        }
        return cur.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("3[a]2[bc]"));
    }
}