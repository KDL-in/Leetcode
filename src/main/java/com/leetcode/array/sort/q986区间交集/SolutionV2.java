package com.leetcode.array.sort.q986区间交集;

import java.util.ArrayList;
import java.util.List;
/*
官方解，极为简洁
关键的问题在于如何简洁判断相交
这件事只要考虑两区间，相交与不相交即可。
* */
class SolutionV2 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }

        return ans.toArray(new int[ans.size()][]);
    }
}