package com.leetcode._byte.q4;

/*
 * 4. Median of Two Sorted Arrays
 * 两个有序数列找中位数
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * */
/*
2092 / 2094 test cases passed.
算法复杂度O(logN logN)
* */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double r = 0;
        int s = nums1.length + nums2.length;
        r = findMid(nums1, nums2, s / 2);
        if ((s & 1) == 0) {
            r += findMid(nums1, nums2, s / 2 - 1);
            r /= 2;
        }
        return r;
    }

    private int findMid(int[] A, int[] B, int M) {
        //assert (M == 1);
        int l, r, m, n;
        l = 0;
        r = A.length;
        while (l < r) {
            // A[m] 左侧有m个
            m = l + (r - l) / 2;
            // B中小于A[m]的有n个
            n = binUpSearch(B, A[m]);
            System.out.println("search " + A[m] + " n " + n);
            if (m + n == M) return A[m];
            else if (m + n > M) r = m;
            else l = m + 1;
        }
        l = 0;
        r = B.length;
        while (l < r) {
            m = l + (r - l) / 2;
            n = binUpSearch(A, B[m]);
            System.out.println("search " + B[m] + " n " + n);
            if (m + n == M) return B[m];
            else if (m + n > M) r = m;
            else l = m + 1;
        }
        return A[A.length-1];
    }

    private int binUpSearch(int[] A, int target) {
        int l = 0, r = A.length, m;
        while (l < r) {
            m = l + (r - l) / 2;
            if (A[m] <= target) l = m + 1;
            else r = m;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(
                new int[]{1, 2, 3},
                new int[]{1,2}
        ));
    }
}

