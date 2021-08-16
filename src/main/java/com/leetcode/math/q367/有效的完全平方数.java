package com.leetcode.math.q367;

class  Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;
        
        long l = 2, r = num / 2 + 1, g;
        while(l < r) {
            long m = l + (r - l) / 2;
            g = m * m;
            if (g == num) return true;
            if (g < num) l = m + 1;
            else r = m;
        }
        return false;
    }
}