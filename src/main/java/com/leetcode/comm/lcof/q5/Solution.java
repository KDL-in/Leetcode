package com.leetcode.comm.lcof.q5;
/*
* 剑指 Offer 05. 替换空格
* https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
* */
/*
水题
* */
class Solution {
    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') builder.append("%20");
            else builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}