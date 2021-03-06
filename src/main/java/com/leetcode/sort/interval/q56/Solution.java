package com.leetcode.sort.interval.q56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 56. Merge Intervals
 * 合并区间
 * https://leetcode.com/problems/merge-intervals/
 *
 * */
/*
思路，对区间进行排序，遍历区间，考察它与前一个是否相交，
相交则两区间相容，改变当前区间。

当前只需要考察前一个区间的原因在于，
- 若与前一个区间相融，则直接融合即可，不需要考察再之前的区间（它们与前一个区间不相容）
- 若与前一个区间不相容，它不可能与更前面的区间相容，更前面的区间与前一个区间不相容（相容则已经被融合再前面一个区间里）

Runtime: 5 ms, faster than 94.78% of Java online submissions for Merge Intervals.
Memory Usage: 41.7 MB, less than 55.95% of Java online submissions for Merge Intervals.

* */
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<Integer> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]));
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] <= intervals[i - 1][1]) {
                // 相交的情况
                intervals[i][0] = intervals[i - 1][0];
                if (intervals[i][1] < intervals[i - 1][1]) intervals[i][1] = intervals[i - 1][1];
            } else {
                // 前一个和当前无相交，则它不可能与之后的有相交
                res.add(i - 1);
            }
        }
        res.add(n - 1);
        int[][] resArray = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = intervals[res.get(i)];
        }
        return resArray;
    }
    //
    // public static void main(String[] args) {
    //     int input[][] = {
    //             {1, 3},
    //             {2, 6},
    //             {8, 10},
    //             {15, 18}
    //     };
    //     new Solution().merge(input);
    // }
}