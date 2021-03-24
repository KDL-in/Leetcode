package com.leetcode.comm.lcof.q16;
/*
50. Pow(x, n)
次方
https://leetcode.com/problems/powx-n/
* */
/*
考虑负数的情况即可。
注意无符号左移，负数的时候和/2是会有不同的，建议考虑>>>直接高位补0
* */
class Solution {
    public double myPow(double x, int n) {
        return n < 0 ? 1 / sPow(x, -n) : sPow(x, n);
    }
    
    public double sPow(double x, int n){
        if (n == 0) return 1;
        double r = sPow(x, n >>> 1);
        r *= r;
        if ((n & 1) == 1) r *= x;
        return r;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE >> 32);
        int n = Integer.MIN_VALUE;
        while (n != 0) {
            System.out.println((n = n / 2));
        }

    }
}