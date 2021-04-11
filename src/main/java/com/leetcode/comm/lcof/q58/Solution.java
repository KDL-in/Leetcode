package com.leetcode.comm.lcof.q58;
/*
* 151. Reverse Words in a String
* 翻转字符串
* https://leetcode.com/problems/reverse-words-in-a-string/
* */
class Solution {
    public String reverseWords(String s) {
        String []buf  = s.trim().split(" ");
        String r = "";
        for(int i = buf.length-1; i >= 0; --i){
            if("".equals(buf[i]))continue;
            if(i == 0) r += buf[i].trim();
            else r += buf[i].trim() + " ";
        }
        return r;
    }
}