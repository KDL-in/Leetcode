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
迭代实现
*/

class SolutionV2 {
    private static int[][] memo;

    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
        memo = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) memo[i][0] = i;
        for (int i = 0; i <= n2; i++) memo[0][i] = i;
        for (int j = 1; j <= n2; j++) {
            for (int i = 1; i <= n1; i++) {
                memo[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? memo[i - 1][j - 1] : Math.min(memo[i - 1][j], memo[i][j - 1]) + 1;
            }
        }
        ArrayTools.disp2DArray(memo);
        return memo[n1][n2];
    }

    public static void main(String[] args) {
        new SolutionV2_1().minDistance(
                "park",
                "spake");
        // ArrayTools.disp2DArray(memo);
    }

}