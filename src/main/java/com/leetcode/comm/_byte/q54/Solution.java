package com.leetcode.comm._byte.q54;

import java.util.ArrayList;
import java.util.List;
/*
* 54. Spiral Matrix
* 螺旋遍历
* https://leetcode.com/problems/spiral-matrix/
*
* */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int i, j, m, n, s, x, y;
        List<Integer> res = new ArrayList<>();
        j = x = 0;
        y = -1; // 保持一致性
        i = 1;
        m = matrix.length;
        n = matrix[0].length;
        s = (Math.min(m , n) + 1) / 2;
        while(s-- > 0){
            while(y+1 < n) res.add(matrix[x][(++y)]);
            if (x + 1 == m) break;
            while(x+1 < m) res.add(matrix[++x][y]);
            if (y - 1 < j) break;
            while(y-1 >= j) res.add(matrix[x][--y]);
            while(x-1 >= i) res.add(matrix[--x][y]);
            --m;
            --n;
            ++i;
            ++j;
        }
        return res;
    }
}