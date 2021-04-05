package com.leetcode.comm.lcof.q49;

import java.util.HashSet;
import java.util.Set;
// 超时
class Solution {
    private Set<Integer> memo;
    public int nthUglyNumber(int n) {
        memo = new HashSet<>();
        memo.add(1);
        memo.add(2);
        memo.add(3);
        memo.add(5);
        int i = 1, k = 0;
        for(i = 1 ; k != n; ++i){
            if(isUgly(i)) ++k;
        }
        return i - 1;
    }
    
    private boolean isUgly(int n){
        if (memo.contains(n)) return true;
        boolean flag = false;
        if (n % 2 == 0) flag  =  flag || isUgly(n/2);
        if (n % 3 == 0) flag = flag || isUgly(n/3);
        if (n % 5 == 0) flag = flag || isUgly(n/5);
        if(flag) memo.add(n);
        return flag;
    }

}