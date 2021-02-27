package com.leetcode.math.pow.q372;

/*
* 372. Super Pow
* 次方模
* https://leetcode.com/problems/super-pow/
*
* */

/*
这道题首先要掌握两方面的知识
- 数学知识，乘积的模等于因子模乘积的模
    $$
    a % b = c \cdots d //
    a * e % b = d * e % b
    $$
- 适用递归高效计算次方，利用指数运算缩小b的位数

算法复杂度分析，sPow调用宽度为1， 深度为O(N)，N为b的位数，sMod为O(1)，所以整体算法复杂度为O(N)
Runtime: 6 ms, faster than 68.60% of Java online submissions for Super Pow.
        Memory Usage: 39.7 MB, less than 20.93% of Java online submissions for Super Pow.
*/

class Solution {
    private int base;


    public int superPow(int a, int[] b) {
        base = 1337;
        return sPow(a, b, b.length);
    }

    private int sMod(int a, int b) {
        int r = 1;
        a = a % base;
        while (b-- > 0) {
            r = (r * a) % b;
        }
        return r;
    }

    private int sPow(int a, int[] b, int n) {
        if (n == 0) return 1;
        int r1 = sMod(a, b[n - 1]);
        int r2 = sMod(sPow(a, b, n - 1), 10);
        return r1 * r2 % base;
    }

}