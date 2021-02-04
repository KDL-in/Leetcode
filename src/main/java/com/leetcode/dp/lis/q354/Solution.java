package com.leetcode.dp.lis.q354;

import java.util.Arrays;

/*
* 354. Russian Doll Envelopes
* 俄罗斯套娃，信封嵌套最多层
* https://leetcode.com/problems/russian-doll-envelopes/
*
* */

/*
lis动态规划解法
Runtime: 340 ms, faster than 10.81% of Java online submissions for Russian Doll Envelopes.
        Memory Usage: 47.5 MB, less than 6.03% of Java online submissions for Russian Doll Envelopes.
*/

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int res = 1;
        int lis[] = new int[envelopes.length];
        Arrays.sort(envelopes, (a, b) -> (a[0] - b[0]));
        Arrays.fill(lis, 1);

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
                res = Math.max(res, lis[i]);
            }
        }
        return res;
    }
}