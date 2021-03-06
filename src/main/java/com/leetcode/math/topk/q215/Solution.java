package com.leetcode.math.topk.q215;

/*
* 215. Kth Largest Element in an Array
* 寻找第k大的数
* https://leetcode.com/problems/kth-largest-element-in-an-array/
*
* */
/*
经典partition算法的使用

时间复杂度，该算法的时间复杂度和划分的平衡性有关，最差情况为逆序序列，则整体为O(N^2)

最好情况下，子问题的规模每次都减半，最佳时间复杂度为O(N)，计算过程见笔记。

Runtime: 7 ms, faster than 28.68% of Java online submissions for Kth Largest Element in an Array.
Memory Usage: 39.2 MB, less than 78.37% of Java online submissions for Kth Largest Element in an Array.
* */

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int i = 0, t = 0, j = nums.length - 1;

        k = nums.length - k;
        while ((t = partition(i, j, nums)) != k) {
            // ArrayTools.disp1DArray(nums);
            if (t > k) j = t - 1;
            else i = t + 1;

        }
        return nums[k];
    }

    private int partition(int bi, int bj, int[] nums) {
        int key = nums[bj], i = bi - 1, j = bj;
        while (i < j) {
            while (nums[++i] < key) ;
            while (j - 1 >= bi && nums[--j] >= key) ;
            swap(i, j, nums);
        }
        swap(i, j, nums);
        swap(bj, i, nums);
        return i;
    }

    private void swap(int i, int j, int[] nums) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // public static void main(String[] args) {
    //     System.out.println(new Solution().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    // }
}