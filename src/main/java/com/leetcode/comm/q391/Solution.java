package com.leetcode.comm.q391;

import static java.lang.Math.*;
/*
* 391. Perfect Rectangle
* 完美覆盖
* https://leetcode.com/problems/perfect-rectangle/
*
* */

/*
覆盖问题，先检测面积，再检测碰撞
Runtime: 640 ms, faster than 5.07% of Java online submissions for Perfect Rectangle.
Memory Usage: 48.1 MB, less than 77.88% of Java online submissions for Perfect Rectangle.
* */
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int sum, min_x1, max_x2, min_y1, max_y2, n;
        int [] a, b;
        
        sum = max_x2 = max_y2 = 0;
        min_x1 = min_y1 = Integer.MAX_VALUE;
        n = rectangles.length;

        for (int i = 0; i < n; ++i) {
            a = rectangles[i];
            sum += (a[2] - a[0]) * (a[3] - a[1]);
            min_x1 = min(min_x1, a[0]);
            max_x2 = max(max_x2, a[2]);
            min_y1 = min(min_y1, a[1]);
            max_y2 = max(max_y2, a[3]);
            for (int j = i+1; j < n; j++) {
                b = rectangles[j];
                if (max(a[0], b[0]) < min(a[2], b[2]) && max(a[1], b[1]) < min(a[2], b[2])) return false;
            }
        }
        if (sum != (max_x2 - min_x1) * (max_y2 - min_y1)) return false;
        return true;
    }
}