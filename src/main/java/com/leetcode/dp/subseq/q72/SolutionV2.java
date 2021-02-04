package com.leetcode.dp.subseq.q72;


/*
 * 72. Edit Distance
 * 编辑距离问题
 * https://leetcode.com/problems/edit-distance/
 *
 * */
/*
迭代：
动态规划经典问题。属于子序列问题之一，但是难点在于想明白匹配算法
子序列反向匹配的框架
base：当i或j为-1的时候，说明另一个字符串已经结束，这时候只需要全部插入或全部删除步即可完成
状态：i，j
选择：插入，删除，替换
dp：dp（i， j）返回解决i，j状态需要的最小步数
Time Limit Exceeded
*/

/*

Runtime: 9 ms, faster than 16.91% of Java online submissions for Edit Distance.
        Memory Usage: 41.2 MB, less than 16.76% of Java online submissions for Edit Distance.
*/

class  SolutionV2 {
    private static int memo[][];
    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }
    public int minDistance(String word1, String word2) {
        int n1, n2;
        n1 = word1.length() + 1;
        n2 = word2.length() + 1;
        memo = new int[n1][n2];
        for (int i = 0; i < n2; ++i) memo[0][i] = i;
        for (int i = 0; i < n1; ++i) memo[i][0] = i;

        for (int j = 1; j < n2; j++)
            for (int i = 1; i < n1; i++)
                memo[i][j] = word1.charAt(i-1) != word2.charAt(j-1) ? min(memo[i - 1][j - 1], memo[i - 1][j], memo[i][j - 1]) + 1 : memo[i - 1][j - 1];
        return memo[n1-1][n2-1];
    }

    // public static void main(String[] args) {
    //     new SolutionV2().minDistance("horse",
    //             "ros");
    //     for (int[] ints : memo) {
    //         for (int anInt : ints) {
    //             System.out.print(anInt + " ");
    //         }
    //         System.out.println();
    //     }
    // }
    /*
    *
1 0 0
2 1 0
2 2 0
3 3 2
4 4 3
    *
    * */
}