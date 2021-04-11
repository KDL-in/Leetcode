package com.leetcode.comm.lcof.q67;
/*
* 8. String to Integer (atoi)
* 字符串转整数
* https://leetcode.com/problems/string-to-integer-atoi/
* */
class Solution {
    public int myAtoi(String s) {
        char buf[] = s.trim().toCharArray();
        int flag = 1;
        int i = 0;
        long r = 0;
        if(buf.length == 0) return (int)r;
        if(buf[i] == '-'){
            ++i;
            flag = -1;
        }else if(buf[i] == '+'){
            ++i;
        }
        for ( ; i < buf.length; ++i){
            if(buf[i] < '0' || buf[i] > '9') break;
            if(r >= Integer.MAX_VALUE) break;
            r = r * 10 + (buf[i] - '0');
        }
        if (flag == 1){
            if(r >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            else return (int)r;
        }
        //System.out.println(r);
        return (r > Integer.MAX_VALUE) ? Integer.MIN_VALUE : (int)(-r);
    }
}