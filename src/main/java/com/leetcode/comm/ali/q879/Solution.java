package com.leetcode.comm.ali.q879;

import com.sun.org.apache.xpath.internal.operations.Mod;

/*
* 879. Profitable Schemes
* 选择盈利模式
* https://leetcode.com/problems/profitable-schemes/
*
* */

/*
超时
* */


class Solution {
    private int MOD = (int) (1e9 + 7);
    private int r = 0;

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        backtrack(0, 0, n, minProfit, group, profit);
        return r;
    }

    private void backtrack(int i, int prof, int n, int minProf, int[] group, int[] profit) {
        //System.out.println(i + " " + prof);
        if (i == group.length) {
            if (prof >= minProf) {
                ++r;
                r %= MOD;
            }
            return;
        }

        if (n >= group[i]) backtrack(i + 1, prof + profit[i], n - group[i], minProf, group, profit);
        backtrack(i + 1, prof, n, minProf, group, profit);

    }
}