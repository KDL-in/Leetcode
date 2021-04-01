package com.leetcode.comm.lcof.q44;

/*
 * 400. Nth Digit
 * 数字序列中的某一位
 * https://leetcode.com/problems/nth-digit/
 *
 * */
/*
注意到，这边开了一个long的数组，使用了start，统一了一位的判断。
使用了(n - 1) / len，统一了位数的判断
使用了string，简化了int取位数的判断
* */



class SolutionV2 {
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
}