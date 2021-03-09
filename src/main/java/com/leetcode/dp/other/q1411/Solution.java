package com.leetcode.dp.other.q1411;
/*
* 1411. Number of Ways to Paint N × 3 Grid
* 上色，阿里笔试题
* https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
* */


import java.util.*;

/*
第n行只和n-1行有关，所以这是一个动态规划的题目。

121类型，随后只能跟着5种上色方法，212 231 232 312，其中两种是121类，两种是123类

123类型，随后只能跟着4种上色方法，212 213 232 312 313，其中三种是121类，两种是123类

当n=1时，121类为a=6，123类为b=6，

n=2时，121类为$3*a+2*b$，123类为$2*a+2*b$

依此类推。

Runtime: 2 ms, faster than 98.72% of Java online submissions for Number of Ways to Paint N × 3 Grid.
Memory Usage: 35.4 MB, less than 93.08% of Java online submissions for Number of Ways to Paint N × 3 Grid.
* */
class Solution {
    public int numOfWays(int n) {
        long a = 6, b = 6, t, mod = (long)(1e9+7);
        for (int i = 1; i < n; ++i){
            t = (a * 3 + b * 2) % mod;
            b = (a * 2 + b * 2) % mod;
            a = t;
        }
        return (int)((a + b) % mod);
    }
}