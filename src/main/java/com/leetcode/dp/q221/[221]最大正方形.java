package com.leetcode.dp.q221;//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 数组 动态规划 矩阵 👍 854 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int max = 0, cur;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                cur = 0;
                while(check(i, j, cur + 1, matrix)) cur++;
                max = Math.max(max, cur);
            }
        }
        return max*max;
    }

    private boolean check(int i, int j, int n, char[][] matrix) {
        if (i + n > matrix.length || j + n > matrix[0].length) return false;
        int x = i + n - 1;
        int y = j + n - 1;
        for (int k = x; k >= i; k--) {
            if (matrix[k][y] != '1') return false;
        }
        for (int k = y; k >= j; k--) {
            if (matrix[x][k] != '1') return false;
        }
        return true;
    }
}
// https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode-solution/
class SolutionV2 {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length, max = 0;
        int dp[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == '1'){
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == '1'){
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = minVal(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    private int minVal(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
