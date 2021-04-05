package com.leetcode.comm.lcof.q51;

import java.util.Arrays;

/*
 * 493. Reverse Pairs
 * 求逆序对
 * https://leetcode.com/problems/reverse-pairs/
 *
 * */
/*
改进的归并排序

计算当前问题的逆序对后，进行归并排序，注意当前的左右子问题都已经计算好逆序对并且各自有序

这时候计算当前子问题的逆序对可以用双指针法在O(N)时间内做快速判断
* */



class  Solution {
    public int reversePairs(int[] nums) {
        return div(0, nums.length, nums);
    }

    private int div(int i, int j, int[] A) {

        if (i + 1 >= j) return 0;
        int count = 0, m = (i + j) / 2, p1, p2;

        count += div(i, m, A);
        count += div(m, j, A);

        // 计算逆序对
        p1 = i;
        p2 = m;
        while (p1 < m && p2 < j) {
            if ((long) A[p2] * 2 < A[p1]) {
                count += m - p1;
                ++p2;
            } else ++p1;
        }
        // sort
        int[] t = Arrays.copyOfRange(A, i, m);
        p1 = 0;
        p2 = m;
        // System.out.println(i + " " + j + " " + t.length);
        while (p1 < t.length && p2 < j) {
            // System.out.println(p1 + " - " + p2);
            if (A[p2] < t[p1])
                A[i++] = A[p2++];
            else
                A[i++] = t[p1++];
        }
        if (p1 < m)
            System.arraycopy(t, p1, A, i, t.length - p1);

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{2, 4, 3, 5, 1}));
    }
}