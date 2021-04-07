package com.leetcode.comm.lcof.q51;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 493. Reverse Pairs
 * 求逆序对
 * https://leetcode.com/problems/reverse-pairs/
 *
 * */
/*
基于一种简单的思想例如，1 3 2 3 1
遍历
当前为1，集合为空，前面有多少个大于2的，构成逆序对
当前为3，集合为1，前面有多少个大于6的
            1 3，前面有多少个大于4的
            1 3 2，前面有多少个大于6的
            1 3 2 3，前面有多少个大于2的
为了求“前面有多少个大于2*nums[i]”，直接查询树状树[1, nums[i]]区间内的数的数量和，得到“前面有多少个小于等于nums[i] * 2”

然后，其实就可以考虑用树状数组来解决问题了，遍历，查询，插入新的数字，更新区间。

最后，只剩下一个问题，树状数组需要开一个[1, nums[i]*2]的数组，nums[i]*2太大，所以这里需要做一下离散化处理，只需保证前后大小一致即可。

* */


class SolutionV2 {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        IndexTree tree = new IndexTree(n * 2);
        // map
        Map<Long, Integer> map = new HashMap<>();
        long[] t = new long[n * 2];
        for (int i = 0; i < n; i++) t[i] = nums[i];
        for (int i = 0; i < n; i++) t[n + i] = (long)nums[i] * 2;
        Arrays.sort(t);
        for (int i = 0; i < 2 * n; i++) map.put(t[i], i);

        // trav
        int r = 0;
        for (int i = 0; i < n; i++) {
            int k = map.get((long)nums[i] * 2);
            //System.out.println(num);
            r += i - tree.query(k+1);
            // System.out.println(nums[i] + " " + tree.query(k+1));
            tree.update(map.get((long)nums[i]) + 1, 1);
        }
        return r;
    }
}

class IndexTree {
    private int[] sum;

    public IndexTree(int n) {
        sum = new int[n+1];
    }

    public void update(int i, int delta) {
        while (i < sum.length) {
            sum[i] += delta;
            i += lowbit(i);
        }
    }

    public int query(int i) {
        int r = 0;
        while (i > 0) {
            r += sum[i];
            i -= lowbit(i);
        }
        return r;
    }

    public int lowbit(int i) {
        return i & (-i);
    }

    public static void main(String[] args) {
        System.out.println(new SolutionV2().reversePairs(new int[]{1, 3, 2, 3, 1}));
    }
}