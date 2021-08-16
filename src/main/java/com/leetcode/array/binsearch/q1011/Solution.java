package com.leetcode.datastructure.array.binsearch.q1011;

/*
* 1011. Capacity To Ship Packages Within D Days
* D次运完所有包裹
* https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
* */
/*
二分搜索
Runtime: 11 ms, faster than 26.40% of Java online submissions for Capacity To Ship Packages Within D Days.
        Memory Usage: 42.1 MB, less than 98.30% of Java online submissions for Capacity To Ship Packages Within D Days.
*/

public class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int lo, hi, mid;
        lo = hi = 0;
        for (int w : weights) {
            hi += w;
            lo = Math.max(w, lo);
        }

        lo = Math.max(hi / D, lo);
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (!isPossible(weights, mid, D)) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private boolean isPossible(int[] weights, int C, int D) {
        int c;
        c = 0;
        for (int w : weights) {
            if (c < w) {
                c = C - w;
                D --;
            } else c -= w;
        }
        return D >= 0;
    }

    // public static void main(String[] args) {
    //     int[] weights = {1, 2, 3, 1, 1};
    //     System.out.println(new Solution().shipWithinDays(weights, 4));
    //     new Solution().isPossible(weights, 2, 4);
    // }
}