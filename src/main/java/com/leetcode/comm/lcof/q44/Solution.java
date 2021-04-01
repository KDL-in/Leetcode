package com.leetcode.comm.lcof.q44;

/*
 * 400. Nth Digit
 * 数字序列中的某一位
 * https://leetcode.com/problems/nth-digit/
 *
 * */
/*
找到规律，但是它有更简洁的解法。
* */



class Solution {
    public int findNthDigit(int n) {
        int base, w, i, r, num;
        long t;
        r = 0;
        base = w = 1;
        while (w < 9 && n - (9 * base * w) > 0) {
            //System.out.println(9 * base * w);
            n -= 9 * base * w;
            base *= 10;
            ++w;
        }

        if (base == 1) {
            r = n;
        } else {
            num = base + (n + w - 1) / w - 1;
            i = n % w;
            if (i == 0) i = w;
            while (i-- > 0) {
                r = num / base;
                num %= base;
                base /= 10;
            }
        }

        return r;
    }
}