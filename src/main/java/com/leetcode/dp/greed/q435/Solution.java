package com.leetcode.dp.greed.q435;

import java.util.Arrays;
/*
* 435. Non-overlapping Intervals
* 删除重叠的区间
* https://leetcode.com/problems/non-overlapping-intervals/
* */
/*
贪心
Runtime: 3 ms, faster than 59.83% of Java online submissions for Non-overlapping Intervals.
        Memory Usage: 38.6 MB, less than 92.34% of Java online submissions for Non-overlapping Intervals.
*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int res = 0, x = 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[x][1] > intervals[i][0]) ++res;
            else x = i;
        }
        return res;
    }
} 