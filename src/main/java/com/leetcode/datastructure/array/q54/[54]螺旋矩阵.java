package com.leetcode.datastructure.array.q54;//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 838 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int x = 0, y = 0, mStep = matrix.length, nStep = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        while (mStep != 0 && nStep != 0){
            for (int i = 0; i < nStep; ++i) list.add(matrix[x][y++]);
            y--; mStep--; x++;
            if (mStep == 0)break;
            for (int i = 0; i < mStep; ++i) list.add(matrix[x++][y]);
            x--; nStep--; y--;
            if (nStep == 0) break;
            for (int i = 0; i < nStep; ++i) list.add(matrix[x][y--]);
            y++; mStep--; x--;
            if (mStep == 0) break;
            for (int i = 0; i < mStep; ++i) list.add(matrix[x--][y]);
            x++; nStep--; y++;
            if (nStep == 0)break;
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
