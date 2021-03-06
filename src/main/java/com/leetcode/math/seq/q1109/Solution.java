package com.leetcode.math.seq.q1109;

/*
 * 1109. Corporate Flight Bookings
 * 订航班，差分
 * https://leetcode.com/problems/corporate-flight-bookings/
 *
 * */
/*
序列差分，适用于连续改变区间

该题可以轻易想到O(N^2)的解，因为直观上bookings需要遍历，区间需要遍历，至少遍历一次。

但其实有一个非常巧妙的的方法，利用了区间连续的特点。按区间变动，在差分数组中，只有区间两侧才会发生变化。
https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/qi-ta-suan-fa-ji-qiao/cha-fen-ji-qiao
Runtime: 3 ms, faster than 72.03% of Java online submissions for Corporate Flight Bookings.
Memory Usage: 54.3 MB, less than 73.37% of Java online submissions for Corporate Flight Bookings.
* */
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];

        for (int[] booking : bookings) {
            diff[booking[0] - 1] += booking[2];
            if (booking[1] < n) diff[booking[1]] -= booking[2];
        }
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i] + diff[i - 1];
        }
        return diff;
    }
}