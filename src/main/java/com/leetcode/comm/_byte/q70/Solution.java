package com.leetcode.comm._byte.q70;
/*
* 70. Climbing Stairs
* 爬楼梯的方式
* https://leetcode.com/problems/climbing-stairs/
* */
/*
入门动态规划
* */
class Solution {
    public int climbStairs(int n) {
        int fn_1 = 1, fn_2 = 1;
        for(int i = 2; i <= n ; ++i){
            int t = fn_2;
            fn_2 = fn_1;
            fn_1 = fn_1 + t;
        }
        return fn_1;
    }
}