package com.leetcode.sort.interval.q56;

import java.util.Arrays;
import java.util.LinkedList;
/*
官方的解，写得很漂亮
* */
class SolutionV2 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                // 这里是巧用引用
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        // 二维数组的toArray
        return merged.toArray(new int[merged.size()][]);
    }
}