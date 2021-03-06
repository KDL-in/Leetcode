package com.leetcode.sort.interval.q1288;

import java.util.Arrays;

/*
* 1288. Remove Covered Intervals
* 删除被完全覆盖的区间
* https://leetcode.com/problems/remove-covered-intervals/
*
* */

/*
先排序，再贪心。排序需要按第一关键字升序，第二关键字降序。

证明贪心：

排序后已经和第一关键字无关（左区间）——i的左区间一定可以被i-1的左区间包括。

只考虑第二关键字。假设a为0到i-1中，第二关键字最大的，即a[1]最大，则

若当前b[1] > a[1]，则后序a所能覆盖所有区间，必定可以被b覆盖。所以后序所有区间只需要检查b即可。

Runtime: 4 ms, faster than 98.21% of Java online submissions for Remove Covered Intervals.
Memory Usage: 39.3 MB, less than 74.78% of Java online submissions for Remove Covered Intervals.
* */
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int count, n, i, j;
        count = i = 0; j = 1;
        n = intervals.length;
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0]) ? b[1] - a[1] : a[0] - b[0]);
        while (j < n) {
            if (intervals[j][1] <= intervals[i][1]) count++;
            else i=j;
            j++;
        }
        return n - count;
    }
}