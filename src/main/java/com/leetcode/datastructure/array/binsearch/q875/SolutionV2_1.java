package com.leetcode.datastructure.array.binsearch.q875;


/*
二分搜索正确模板
Runtime: 8 ms
Memory Usage: 40.2 MB

*/


class Solution2_1 {
    public int minEatingSpeed(int[] piles, int H) {
        int low, high, mid, d;
        high = piles[0]; low = 1;
        // 优化1，Math库稍微快点
        for (int p : piles) high = Math.max(p, high);

        // 二分搜索的模板，左闭右开，边界条件也恰当好处
        while (low < high) {
            mid = (low + high) >> 1;
            d = 0;
            // 优化4，不调用多余函数
            for (int p : piles) {
                // 优化2，好像比(p - 1) / mid + 1 稍微快点，因为加法聚集
                d += (p - 1 + mid) / mid;
            }
            // 优化3，比<=要稍微快点，因为只有一个条件
            if (d > H) low = mid + 1;
            else high = mid;
        }
        // System.out.println(low + " " + high);
        return low;
    }

}