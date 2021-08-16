package com.leetcode.datastructure.array.binsearch.q1011;


/*

Runtime: 10 ms, faster than 35.69% of Java online submissions for Capacity To Ship Packages Within D Days.
Memory Usage: 41.8 MB, less than 99.32% of Java online submissions for Capacity To Ship Packages
*/

public class SolutionV1_1 {
    public int shipWithinDays(int[] weights, int D) {
        int lo, hi, mid, c, d;
        lo = 0;
        hi = 50000 * 500;
        for (int w : weights) {
            lo = Math.max(w, lo);
        }

        while (lo < hi) {
            mid = (lo + hi) / 2;
            d = c = 0;
            for (int w : weights) {
                if (c < w) {
                    c = mid - w;
                    d++;
                } else c -= w;
            }
            if (d > D) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}