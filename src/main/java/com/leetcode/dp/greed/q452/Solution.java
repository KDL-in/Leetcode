package com.leetcode.dp.greed.q452;

/*
 * 452. Minimum Number of Arrows to Burst Balloons
 * 射气球
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * */

import java.util.Arrays;


/*
贪心
Runtime: 14 ms, faster than 99.53% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
Memory Usage: 46.7 MB, less than 54.97% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
* */
class Solution {
    public int findMinArrowShots(int[][] points) {
        int res = 1;
        Arrays.sort(points, (a, b) -> a[1] > b[1] ? 1 : -1);
        if (points.length == 0) return 0;
        int[] x = points[0];
        for (int[] point : points) {
            if (point[0] > x[1]) {
                x = point;
                ++res;
            }
        }
        return res;
    }
}