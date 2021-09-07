package com.leetcode.array.q498;//给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
//
// 
//
// 示例: 
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//输出:  [1,2,4,7,5,3,6,8,9]
//
//解释:
//
// 
//
// 
//
// 说明: 
//
// 
// 给定矩阵中的元素总数不会超过 100000 。 
// 
// Related Topics 数组 矩阵 模拟 👍 230 👎 0
/*
经典对角线遍历，确实比较难考虑清晰，需要正确的切入角度。

其核心在于`x + y = i` ，x -> 0， y -> m。

由于该题加上了正反顺序变化，使得看起来比较复杂。

如果没有正反顺序，改代码为：

````
        for (int i = 0; i < n + m; i++) {
            x = i < n ? i : n - 1;
            y = i - x;
            while (x >= 0 && y < m) {
                print(mat[x][y])
                x --;
                y ++;
            }
        }
````

如果该矩阵为n阶矩阵：

````
        for (int i = 0; i < n + n; i++) {
            x = i < n ? i : n - 1;
            y = i - x;
            while (x >= 0 && y < n) {
                print(mat[x][y])
                x --;
                y ++;
            }
        }
````

 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length, idx = 0;
        int[] res = new int[n * m];
        int x, y, cn, cm;
        boolean flag = true;
        for (int i = 0; i < n + m; i++) {
            cn = flag ? n : m;
            cm = flag ? m : n;
            x = i < cn ? i : cn - 1;
            y = i - x;
            while (x >= 0 && y < cm) {
                res[idx++] = flag ? mat[x][y] : mat[y][x];
                x --;
                y ++;
            }
            flag = !flag;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        int n = mat.length;
        // 正对角线遍历 142753869
        for (int i = 0; i < n + n; i++) {
            int x = i < n ? i : n - 1;
            int y = i - x;
            while (x >= 0 && y < n){
                System.out.print(mat[x--][y++]);
            }
        }
        System.out.println();
        // 副对角线遍历（上半部分）159263
        for (int k = 0; k < n; k++) {
            for (int i = 0, j = k; j < n; i ++, j++) {
                System.out.print(mat[i][j]);
            }
        }
        System.out.println();
    }
}


//leetcode submit region end(Prohibit modification and deletion)
