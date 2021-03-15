package com.leetcode.comm._byte.q415;
/*
* 415. Add Strings
* 模拟加法
* https://leetcode.com/problems/add-strings/
*
* */
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder str = new StringBuilder();
        int i, j, ab, t;
        i = num1.length() - 1;
        j = num2.length() - 1;
        ab = 0;
        while (i >= 0 || j >= 0) {
            t = 0;
            if (i >= 0) t += (num1.charAt(i--) - '0');
            if (j >= 0) t += (num2.charAt(j--) - '0');
            t += ab;
            str.append(t % 10);
            ab = t / 10;
        }
        if (ab!=0) str.append(ab);
        return str.reverse().toString();
    }
}