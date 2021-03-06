package com.leetcode.comm.q43;

/*
 * 43. Multiply Strings
 * 模拟乘法
 * https://leetcode.com/problems/multiply-strings/
 * */

/*
反向计算是比较好的实现。

应该使用int数组来模拟比较方便点，先申请足够的位置，就不必考虑add增加位置的问题。
Runtime: 7 ms, faster than 38.57% of Java online submissions for Multiply Strings.
Memory Usage: 38.6 MB, less than 95.77% of Java online submissions for Multiply Strings.
* */
class Solution {
    public String multiply(String num1, String num2) {
        int n, m, a1, a2, t;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();
        n = num1.length();
        m = num2.length();
        for (int i = 0; i < m; i++) res.append(0);
        for (int i = 0; i < n; i++) {
            a1 = a2 = 0;
            for (int j = 0; j < m; j++) {
                t = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + a1;
                a1 = t / 10;
                t = t % 10 + a2 + (res.charAt(i + j) - '0');
                res.setCharAt(i + j, (char) ((t % 10) + '0'));
                a2 = t / 10;
            }
            if (a1 != 0 || a2 != 0) res.append((char)(a1  + a2 + '0'));
            else res.append('0');

            // System.out.println(res.toString());
        }
        int i;
        for (i = res.length()-1; i > 0; --i) {
            if (res.charAt(i)!='0') break;
        }
        res.setLength(i + 1);
        return res.reverse().toString();
    }
}