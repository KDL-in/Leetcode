package com.leetcode.comm.lcof.q49;

import java.util.HashSet;
import java.util.Set;
// 超时
class SolutionV2 {
    private Set<Integer> memo;
    public int nthUglyNumber(int n) {
        memo = new HashSet<>();
        memo.add(1);

        int i, k = 1;
        for(i = 1; ; ++i){
            //System.out.println(i);
            if (i % 2 == 0 && memo.contains(i / 2)) {
                memo.add(i);
                ++k;
            } else if (i % 3 == 0 && memo.contains(i / 3)) {
                memo.add(i);
                ++k;
            } else if (i % 5 == 0 && memo.contains(i / 5)) {
                memo.add(i);
                ++k;
            }
            if (k == n)break;
        }
        return i;
    }


    public static void main(String[] args) {
        System.out.println(new SolutionV2().nthUglyNumber(1352));
    }

}