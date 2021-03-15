package com.leetcode.comm._byte.q42;
/*
* 42. Trapping Rain Water
* 接雨水
* https://leetcode.com/problems/trapping-rain-water/
*
* */
/*
这个题目可以很容易想到二次方复杂度的解法，横向遍历。直觉上，应该能够在O(N)复杂度解决。

用空间换时间，只要知道每一个点是否后面有比它高的元素即可。求覆盖数组。
* */
class Solution {
    public int trap(int[] height) {
        int l = 0, r,t, sum = 0, n = height.length, max=0;
        int c[] = new int[n];
        for(int i = n-1; i >=0; --i) {
            if(height[i] <= max) c[i] = max;
            else {
                c[i] = max;
                max = height[i];
            }
            //System.out.print(c[i] + " ");            
        }

        while(l < n){
            r = l + 1;
            if (c[l]!=0){
                t =Math.min(height[l], c[l]);
                while(r < n && t > height[r]){
                    sum -= height[r++];
                }
                sum += t * (r - l -1);
                //System.out.println(l + " " + r + " " + sum);
            }
            //System.out.print(sum + " ");
            l = r;
        }
        return sum;
    }
}