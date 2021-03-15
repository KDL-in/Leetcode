package com.leetcode.comm.other.q5;
/*
* 5. Longest Palindromic Substring
* 最长回文字串
* https://leetcode.com/problems/longest-palindromic-substring/
*
* */
class Solution {
    public String longestPalindrome(String s) {
        String r, s1, s2;
        int n = s.length();
        r = "";
        for (int i = 0; i < n; ++i){
            s1 = paliStr(i, i, s);
            s2 = paliStr(i, i+1, s);
            if(r.length() < s1.length()) r = s1;
            if(r.length() < s2.length()) r = s2;
        }
        return r;
    }
    
    private String paliStr(int i, int j, String s){
        int n = s.length();
        while(i >= 0 && j < n && s.charAt(i) == s.charAt(j)){--i; ++j;}
        return s.substring(i+1, j);
    }
}