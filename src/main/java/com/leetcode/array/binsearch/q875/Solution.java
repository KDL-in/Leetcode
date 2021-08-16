package com.leetcode.datastructure.array.binsearch.q875;

/*
* 875. Koko Eating Bananas
* 吃完所有香蕉
* https://leetcode.com/problems/koko-eating-bananas/
*
* */
/*
二分搜索
Runtime: 36 ms, faster than 14.71% of Java online submissions for Koko Eating Bananas.
Memory Usage: 40.3 MB, less than 32.00% of Java online submissions for Koko Eating Bananas.
*/

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        long s; int low, high, mid, d;
        s = 0; high = piles[0];

        for (int p : piles) {
            s += p;
            if (high < p) high = p;
        }
        low = (int)(s - 1) / H + 1;
        // System.out.println(low + " " + s);
        while (low < high) {
            mid = (low + high) / 2;
            d = eating(piles, mid);
            // System.out.println(s + " low: " + low + " high: " + high);
            if (d <= H) high = mid;
            else low = mid + 1;
        }
        // System.out.println(low + " " + high);
        return low;
    }

    private int eating(int[] piles, int speed) {
        int d = 0;
        for (int p : piles) {
            d += (p - 1) / speed + 1;;
        }
        return d;
    }
}