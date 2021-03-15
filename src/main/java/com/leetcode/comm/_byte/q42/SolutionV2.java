package com.leetcode.comm._byte.q42;
/*
非常好实现的一种方法，计算左边最高的数组和右边最高的数组，然后遍历一遍解决。
Runtime: 1 ms, faster than 85.69% of Java online submissions for Trapping Rain Water.
Memory Usage: 38.3 MB, less than 92.10% of Java online submissions for Trapping Rain Water.
* */
public class SolutionV2 {
    public int trap(int[] height) {
        int res, n;
        int [] lHeight, rHeight;
        res = 0;
        n = height.length;
        if (n==0) return 0;
        lHeight = new int[n];
        rHeight = new int[n];
        lHeight[0] = height[0];
        rHeight[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) lHeight[i] = Math.max(height[i], lHeight[i - 1]);
        for (int i = n-2; i >= 0; i--) rHeight[i] = Math.max(height[i], rHeight[i + 1]);
        for (int i = 0; i < n; i++) {
            res += Math.min(rHeight[i], lHeight[i]) - height[i];
        }
        return res;
    }
}
