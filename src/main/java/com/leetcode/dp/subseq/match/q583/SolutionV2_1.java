package com.leetcode.dp.subseq.match.q583;

/*
* 583. Delete Operation for Two Strings
* 序列匹配，删除以相同
* https://leetcode.com/problems/delete-operation-for-two-strings/
*
* */

/*


*/


import com.leetcode.common.array.ArrayTools;
/*
O(N)空间复杂度优化
Runtime: 5 ms, faster than 98.95% of Java online submissions for Delete Operation for Two Strings.
Memory Usage: 38.7 MB, less than 99.89% of Java online submissions for Delete Operation for Two Strings.
*/

class SolutionV2_1 {

    public int minDistance(String word1, String word2) {

        int[] dp;
        int n1 = word1.length(), n2 = word2.length(), t, pre = 0;
        dp = new int[n1 + 1];
        for (int i = 0; i <= n1; i++) dp[i] = i;
        ArrayTools.disp1DArray(dp);
        for (int j = 1; j <= n2; j++) {
            pre = dp[0];
            dp[0] = j;
            for (int i = 1; i <= n1; i++) {
                t = word1.charAt(i - 1) == word2.charAt(j - 1) ? pre :( Math.min(dp[i - 1], dp[i]) + 1);
                pre = dp[i];
                dp[i] = t;
            }
        }

        return dp[n1];
    }
}

/*

0 1 2 3 4 5
1 2 1 2 3 4
2 3 2 1 2 3
3 4 3 2 3 4
4 5 4 3 2 3 */
