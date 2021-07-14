package com.leetcode._byte.q42;
/*
双指针，极其强大的方式，其实本质上和V2类似

它的直觉是，左边比右边小的时候，一定可以留下和左边一样高的水，反之，同理。
* */
public class SolutionV3 {
    public int trap(int[] height) {
        int res, lh, rh, l, r;
        res = l = lh = rh = 0;
        r = height.length - 1;
        while (l < r) {
            lh = Math.max(lh, height[l]);
            rh = Math.max(rh, height[r]);
            if (rh > lh) {
                res += lh - height[l++];
            } else {
                res += rh - height[r--];
            }
        }
        return res;
    }
}
