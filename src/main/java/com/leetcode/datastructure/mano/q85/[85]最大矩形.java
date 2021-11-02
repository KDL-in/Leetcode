package com.leetcode.datastructure.mano.q85;//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1072 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] sum = new int[matrix[0].length + 2];
        int ans = 0;
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum[j + 1] = chars[j] == '0' ? 0 : sum[j + 1] + 1;
                //System.out.print(sum[j+1] + " ");
            }
            //System.out.println();
            ans = Math.max(ans, maxRectangle(sum));
            //System.out.println(ans);
        }
        return ans;
    }

    private int maxRectangle(int[] sum) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < sum.length; ) {
            if (sum[i] >= sum[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = sum[stack.pop()];
                ans = Math.max(ans, height * (i - stack.peek() - 1));
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
