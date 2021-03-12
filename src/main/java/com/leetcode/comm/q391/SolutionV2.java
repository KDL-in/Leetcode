package com.leetcode.comm.q391;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;
/*
* 391. Perfect Rectangle
* 完美覆盖
* https://leetcode.com/problems/perfect-rectangle/
*
* */

/*
O(N)的解法，顶点数为4，内部顶点，完美覆盖会自动消掉
覆盖问题，先检测面积，再检测碰撞
Runtime: 35 ms, faster than 84.33% of Java online submissions for Perfect Rectangle.
Memory Usage: 48.9 MB, less than 26.73% of Java online submissions for Perfect Rectangle.
* */
class SolutionV2 {
    public boolean isRectangleCover(int[][] rectangles) {
        int sum, x1, x2, y1, y2, n;
        int [] a;
        Set<String> corner = new HashSet<>();
        String cur;

        sum = x2 = y2 = 0;
        x1 = y1 = Integer.MAX_VALUE;
        n = rectangles.length;

        for (int i = 0; i < n; ++i) {
            a = rectangles[i];
            sum += (a[2] - a[0]) * (a[3] - a[1]);
            x1 = min(x1, a[0]);
            x2 = max(x2, a[2]);
            y1 = min(y1, a[1]);
            y2 = max(y2, a[3]);
            for (int j = 0; j < 4; j+=2) {
                for (int k = 1; k < 4; k+=2) {
                    if (corner.contains(cur = (a[j] + " " + a[k]))) corner.remove(cur);
                    else corner.add(cur);
                }
            }
        }
        if (corner.size()==4 && corner.contains(x1 + " " + y1) && corner.contains(x1 + " " + y2)
            && corner.contains(x2 + " " + y1) && corner.contains(x2 + " " + y2) && sum == (x2 - x1) * (y2 - y1))
            return true;

        return false;
    }
}