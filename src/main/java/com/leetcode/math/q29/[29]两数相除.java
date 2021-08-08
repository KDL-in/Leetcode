package com.leetcode.math.q29;//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 
// 👍 627 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        long a = dividend, b = divisor;
        if (dividend < 0) {
            a = -a;
            sign *= -1;
        }
        if (divisor < 0){
            b = -b;
            sign *= -1;
        }
        long r = div(a, b);
        if (r == ((long)Integer.MAX_VALUE + 1)){
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return sign * (int)r;
    }

    private long div(long a, long b){
        long r = 0, t = b, n = 1;
        while (a >= t) {
            if (a >= b) {
                a -= b;
                r += n;
            }
            if (a > b){
                b <<= 1;
                n <<= 1;
            } else if (a < b) {
                b >>= 1;
                n >>= 1;
            }
        }
        return r;
    }
//    public static void main(String[] args) {
//        new Solution().divide(Integer.MIN_VALUE, -1);
//    }
}
class SolutionV2 {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //Cornor case when -2^31 is divided by -1 will give 2^31 which doesnt exist so overflow

        boolean negative = dividend < 0 ^ divisor < 0; //Logical XOR will help in deciding if the results is negative only if any one of them is negative

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0, subQuot = 0;

        while (dividend - divisor >= 0) {
            for (subQuot = 0; dividend - (divisor << subQuot << 1) >= 0; subQuot++);
            quotient += 1 << subQuot; //Add to the quotient
            dividend -= divisor << subQuot; //Substract from dividend to start over with the remaining
        }
        return negative ? -quotient : quotient;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
