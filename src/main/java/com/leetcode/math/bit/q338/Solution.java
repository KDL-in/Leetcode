package com.leetcode.math.bit.q338;

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = countBit(i);
        }
        return res;
    }

    private int countBit(int n) {
        int r = 0;
        while (n != 0){
            n &= (n-1);
            r++;
        }
        return r;
    }
}