package com.leetcode.comm.lcof.q51.q327;
/*
* 327. Count of Range Sum
* 区间和位于某区间的数量
* https://leetcode.com/problems/count-of-range-sum/
* */
/*
区间和题目，使用归并排序

- 首先将区间和转化为前缀差
- 其次是两个有序数组，如何求出所有A[j] - B[i]在区间内的问题。
    https://leetcode-cn.com/problems/count-of-range-sum/solution/qu-jian-he-de-ge-shu-by-leetcode-solution/
- 然后将数组归并排序
* */


import java.util.Arrays;

class Solution {
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        
        long[] A  = new long[n];
        A[0] = nums[0];
        // preSum
        for(int i = 1; i < n; ++ i) A[i] += nums[i] + A[i-1];
        // div merge sort
        int r = div(A, 0, n-1, lower, upper);
        
        return r;
    }
    private int div(long [] A, int i, int j, int l, int u){
        if (i == j) return (A[i] >= l && A[i] <= u) ? 1 : 0;
        int r, m, p, pl, pr;
        r = 0;
        m = i + (j - i) / 2;
        //div
        r += div(A, i, m, l, u);
        r += div(A, m+1, j, l, u);
        //conq
        pl = pr = m + 1;
        for(p = i; p <= m; ++p){
            while(pl <= j && A[pl] - A[p] < l) ++pl;
            while(pr <= j && A[pr] - A[p] <= u) ++pr;
            r += pr - pl;
        }
        // merge sort
        long t[] = Arrays.copyOfRange(A, i, m+1);
        pl = 0;
        pr = m + 1;
        while(pl < t.length && pr <= j){
            if(A[pr] < t[pl]) A[i++] = A[pr++];
            else A[i++] = t[pl++];
        }
        if(pl < t.length){
            System.arraycopy(t, pl, A, i, t.length - pl);
        }
        return r;
        
        
    }
}