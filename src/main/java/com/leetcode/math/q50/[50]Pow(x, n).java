package com.leetcode.math.q50;
//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x⁴
//
// Related Topics 递归 数学 👍 731 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        if (x == 1 || n == 0) return 1;
        return n < 0 ? 1.0 / pow(x, -n) : pow(x, n);
    }

    private double pow(double x, int n) {
        if (n == 1) return x;
        // 无符号右移，解决n=Integer.MAX_MIN的问题
        double r = pow(x, n >>> 1);
        r = r * r;
        if ((n & 1) == 1) r *= x;
        return r;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
