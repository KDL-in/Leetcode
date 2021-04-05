package com.leetcode.comm.lcof.q49;
/*
264. Ugly Number II
https://leetcode.com/problems/ugly-number-ii/
第n个丑数
* */
/*
动态规划，解法不好想，三指针法，直观的理解是，每个大的丑数都必然由小的丑数乘以2 3 5得来。0
* */
class SolutionV3 {
    
    public int nthUglyNumber(int n) {
        
        int A[] = new int[1700];
        int a, b, c, m, k;
        k = 1;
        a = b = c = A[k++] = 1;
        
        while( k <= n){
            m = Math.min(Math.min(A[a] * 2, A[b] * 3), A[c] * 5);
            if(m == A[a] * 2) a++;
            if(m == A[b] * 3) b++;
            if(m == A[c] * 5) c++;
            A[k++] = m;
        }
        return A[n];
    }



}