package com.leetcode.dp.subseq.match.q72;


/*
* 72. Edit Distance
* 编辑距离问题
* https://leetcode.com/problems/edit-distance/
*
* */
/*
备忘录：
动态规划经典问题。属于子序列问题之一，但是难点在于想明白匹配算法
子序列反向匹配的框架
base：当i或j为-1的时候，说明另一个字符串已经结束，这时候只需要全部插入或全部删除步即可完成
状态：i，j
选择：插入，删除，替换
dp：dp（i， j）返回解决i，j状态需要的最小步数

Runtime: 4 ms, faster than 92.41% of Java online submissions for Edit Distance.
Memory Usage: 38.9 MB, less than 88.01% of Java online submissions for Edit Distance.
*/

class SolutionV1 {
    private static int[][] memo;

    public int dp(int i, int j, String word1, String word2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j] != 0) return memo[i][j];
        memo[i][j] =  word1.charAt(i) != word2.charAt(j) ?
            min(dp(i-1, j-1, word1, word2) + 1, dp(i, j - 1, word1, word2) + 1,
                    dp(i - 1, j, word1, word2) + 1) : dp(i - 1, j - 1, word1, word2);
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        return dp(word1.length() - 1, word2.length() - 1, word1, word2);
    }

    public static void main(String[] args) {
        new SolutionV1().minDistance("horse",
                "ros");
        for (int[] ints : memo) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}