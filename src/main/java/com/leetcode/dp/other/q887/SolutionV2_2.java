package com.leetcode.dp.other.q887;


/*
迭代
Runtime: 1 ms, faster than 87.50% of Java online submissions for Super Egg Drop.
Memory Usage: 39.6 MB, less than 44.64% of Java online submissions for Super Egg Drop.
 */

public class SolutionV2_2 {


    public int superEggDrop(int K, int N) {
        int[][] memo = new int[K + 1][N + 1];
        int m = 1;

        for (m = 1; ; m++) {
            for (int k = 1; k <= K; k++) {
                memo[k][m] = memo[k - 1][m - 1] + memo[k][m - 1] + 1;
            }
            System.out.println(memo[K][m]);
            if (memo[K][m] >= N) break;
        }
        return m;
    }

    public static void main(String[] args) {
        new SolutionV2_2().superEggDrop(1, 4);
    }

}
