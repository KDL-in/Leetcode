package com.leetcode.comm.lcof.q49.q263;
/*
263. Ugly Number
https://leetcode.com/problems/ugly-number/
丑数，只包含2 3 5的因子，这个写法就很简洁
* */
class Solution {

    // kittyfeng's avatar
    // kittyfeng
    // 92
    // Last Edit: October 22, 2018 3:36 AM

    // 22.9K VIEWS

    public boolean isUgly(int num) {
        if (num == 1) return true;
        if (num == 0) return false;
        while (num % 2 == 0) num = num >> 1;
        while (num % 3 == 0) num = num / 3;
        while (num % 5 == 0) num = num / 5;
        return num == 1;
    }
}