package com.leetcode.dp.q329;//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4 
//解释：最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2： 
//
// 
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4 
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// 0 <= matrix[i][j] <= 2³¹ - 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 👍 522 👎 0


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        ++memo[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }
}

class SolutionV2 {

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 1;
        int[][] d = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };
        int[][] memo = new int[matrix.length][matrix[0].length];
        Queue<Point> q = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.v - o2.v;
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length;j++) {
                q.add(new Point(matrix[i][j], i, j));
                memo[i][j] = 1;
            }
        }
        while (!q.isEmpty()) {
            Point p = q.poll();
            int max = 0;
            for (int i = 0; i < 4; i++) {
                int x = p.x + d[i][0];
                int y = p.y + d[i][1];
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || p.v <= matrix[x][y]) continue;
                max = Math.max(max, memo[x][y]);
            }
            memo[p.x][p.y] = max + 1;
            ans = Math.max(memo[p.x][p.y], ans);
        }
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length;j++) {
//                System.out.print(memo[i][j]+ " ");
//            }
//            System.out.println();
//        }
        return ans;
    }
}
class Point{
    int v, x, y;

    public Point(int v, int x, int y) {
        this.v = v;
        this.x = x;
        this.y = y;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
