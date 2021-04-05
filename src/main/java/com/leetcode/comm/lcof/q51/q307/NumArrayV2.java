package com.leetcode.comm.lcof.q51.q307;

/*
 * 307. Range Sum Query - Mutable
 * 维护查询区间和，经典树状树
 * https://leetcode.com/problems/range-sum-query-mutable/
 * https://www.youtube.com/watch?v=WbafSgetDDk
 *
 * */
/*
线段树的数据结构比较特殊，它维护的是部分和。
它的下标变化如下
1 -> 2 -> 4 -> 8
3 -> 4 -> 8
5 -> 6 -> 8
7 -> 8
我还不能一种直观的方式去理解这个过程，但是由于这个机制，它整体上
可以实现区间的更新和查询，建树是O(N log N)，查询更新都是O(log N)
而且它实现非常简洁，注意下标从1开始

* */
class NumArrayV2 {

    private FenwickTree tree;
    private int[] nums;
    public NumArrayV2(int[] nums) {
        tree = new FenwickTree(nums);
        this.nums = nums;
    }

    public void update(int index, int val) {
        tree.update(index, val - nums[index]);
        this.nums[index] = val;
    }


    public int sumRange(int left, int right) {
        return tree.query(right) - tree.query(left-1);
    }

}

class FenwickTree {
    private int sum[];
    private int n;

    public FenwickTree(int[] nums) {
        n = nums.length + 1;
        this.sum = new int[n];
        for (int i = 0; i < nums.length; i++) update(i, nums[i]);
    }

    public void update(int i, int delta) {
        i += 1;
        while (i < n) {
            sum[i] += delta;
            i += lowbit(i);
        }
    }

    public int query(int i) {
        i += 1;
        int r = 0;
        while (i > 0) {
            r += sum[i];
            i -= lowbit(i);
        }
        return r;
    }

    private int lowbit(int i) {
        return i & (-i);
    }

    public static void main(String[] args) {
        NumArrayV2 arr = new NumArrayV2(new int[]{1, 3, 5});

    }

}