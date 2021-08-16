package com.leetcode.datastructure.array.binsearch.q875;


/*
二分搜索正确模板
// d += (int) Math.ceil(p * 1.0 / speed );
Runtime: 36 ms, faster than 14.71% of Java online submissions for Koko Eating Bananas.
Memory Usage: 40.3 MB, less than 43.73% of Java online submissions for Koko Eating Bananas.
d += (p - 1) / speed + 1;
Runtime: 10 ms, faster than 79.68% of Java online submissions for Koko Eating Bananas.
Memory Usage: 52.3 MB, less than 5.27% of Java online submissions for Koko Eating Bananas.

*/


class SolutionV2 {
    public int minEatingSpeed(int[] piles, int H) {
        int low, high, mid, d;
        high = piles[0]; low = 1;

        for (int p : piles) if (high < p) high = p;

        // 二分搜索的模板，左闭右开，边界条件也恰当好处
        while (low < high) {
            mid = (low + high) >> 1;
            d = eating(piles, mid);
            // 边界条件
            if (d <= H) high = mid;
            else low = mid + 1;
        }
        // System.out.println(low + " " + high);
        return low;
    }

    private int eating(int[] piles, int speed) {
        int d = 0;
        for (int p : piles) {
            // d += (int) Math.ceil(p * 1.0 / speed );
            d += (p - 1) / speed + 1;
        }
        return d;
    }
}