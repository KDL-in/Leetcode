package com.leetcode.comm.q969;

import java.util.ArrayList;
import java.util.List;
/*
* 969. Pancake Sorting
* 翻烧饼排序
* https://leetcode.com/problems/pancake-sorting/
*
* */

/*
思路，由于只能从开头翻转数组，考虑每次使得最大的值有序。

需要先将最大值翻转到0处，再将其翻转到n正确位置。
Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
Memory Usage: 39.1 MB, less than 63.48% of Java online submissions for Pancake Sorting.
* */
class Solution {
    public List<Integer> pancakeSort(int[] arr) {
        int n,j, max;
        List<Integer> res = new ArrayList<>();
        n = arr.length;
        while (n-- > 0) {
            j = n;
            max = arr[j];
            for (int i = 0; i < n; i++) {
                if (arr[i] > max) {
                    j = i;
                    max = arr[j];
                }
            }
            flip(j, arr);
            res.add(j + 1);
            flip(n, arr);
            res.add(n + 1);
        }
        return res;
    }

    private void flip(int j, int[] arr) {
        int i = 0, t;
        while (i < j) {
            t = arr[i];
            arr[i++] = arr[j];
            arr[j--] = t;
        }
    }
}