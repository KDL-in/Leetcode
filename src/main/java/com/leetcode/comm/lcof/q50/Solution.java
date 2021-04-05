package com.leetcode.comm.lcof.q50;

/*
387. First Unique Character in a String
第一唯一的字母
https://leetcode.com/problems/first-unique-character-in-a-string/
* */
/*
直接hash解决
* */



class Solution {
    public int firstUniqChar(String s) {
        int n = s.length();
        int[] map = new int[255];
        for (int i = 0; i < n; ++i) map[s.charAt(i)]++;
        for (int i = 0; i < n; ++i) {
            if (map[s.charAt(i)] == 1) return i;
        }
        return -1;
    }
}