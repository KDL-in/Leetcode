package com.leetcode.array.sort.q986区间交集;

import java.util.ArrayList;
import java.util.List;

/*
* 986. Interval List Intersections
* 两有序区间列表求交集
* https://leetcode.com/problems/interval-list-intersections/
*
* */

/*
思路，由于两个列表是有序区间，且区间内不相交。则遍历列表1，每次只需要考察列表2当前的区间即可。

然后就是有点乱的逻辑判断。假设a和b分别为两列表当前的区间。



Runtime: 3 ms, faster than 65.36% of Java online submissions for Interval List Intersections.
        Memory Usage: 40 MB, less than 48.55% of Java online submissions for Interval List Intersections.
实际上我这里的实现太复杂了
# A, B 形如 [[0,2],[5,10]...]
def intervalIntersection(A, B):
    i, j = 0, 0 # 双指针
    res = []
    while i < len(A) and j < len(B):
        a1, a2 = A[i][0], A[i][1]
        b1, b2 = B[j][0], B[j][1]
        # 两个区间存在交集
        if b2 >= a1 and a2 >= b1:
            # 计算出交集，加入 res
            res.append([max(a1, b1), min(a2, b2)])
        # 指针前进
        if b2 < a2: j += 1
        else:       i += 1
    return res
*/

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i, j, n, m;
        int[] a, b;
        List<int []> res = new ArrayList<>();
        n = firstList.length; m = secondList.length;
        i = j = 0;
        while (i < n && j < m) {
            a = firstList[i];
            b = secondList[j];
            if (a[0] <= b[1]) {
                //a[0]<=b[1]，满足了相交第一个条件
                if (a[1] >= b[0]) {
                    // a[1] >= b[0] a[0] <= b[1]，必然产生交集
                    res.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
                    if (a[1] <= b[1]) i++; // 当前a和当前的b有交集，但不可能与之后的b有交集
                    else j++; // 当前a和b有交集，但b不可能与之后的a有交集
                } else {
                    // a[1] < b[0], a不可能与之后的b有相交
                    i++;
                }
            } else {
                //a[0]>b[1]，可确定当前b不可能和之后的a有交集
                j++;
            }
        }
        int [][]resArray = new int[res.size()][];
        for (int k = 0; k < res.size(); k++) {
            resArray[k] = res.get(k);
        }
        return resArray;
    }
}