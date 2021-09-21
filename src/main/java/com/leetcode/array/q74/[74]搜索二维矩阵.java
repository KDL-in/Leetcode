package com.leetcode.array.q74;//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
// Related Topics 数组 二分查找 矩阵 👍 506 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int l = 0, r = n * m, mid, row, col;
        while (l < r) {
            mid = l + (r - l) / 2;
            row = mid / m;
            col = mid % m;
            if (matrix[row][col] == target) return true;
            if (matrix[row][col] < target) l = mid + 1;
            else r = mid;
        }
        if (l < n * m) {
            row = l / m;
            col = l % m;
            if (matrix[row][col] == target) return true;
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
