package com.leetcode.comm.lcof.q51.q307;

import java.util.Queue;
/*
* 307. Range Sum Query - Mutable
* 维护查询区间和，经典线段树
* https://leetcode.com/problems/range-sum-query-mutable/
* https://www.youtube.com/watch?v=rYBtViWXYeI
* */
/*
线段树的思想是维护一棵区间和的树。适用于query区间和的场景，建树的复杂度是O(N)，query的复杂度是O(log N)
* */
class NumArray {
    private Node root;

    public NumArray(int[] nums) {
        root = buildTree(0, nums.length-1, nums);
    }

    public void update(int index, int val) {
        update(index, val, root);
    }


    public int sumRange(int left, int right) {
        return query(left, right, root);
    }

    private int query(int i, int j, Node root) {
        if (i == root.i && j == root.j) return root.sum;
        int m = root.i + (root.j - root.i) / 2;
        if (j <= m) return query(i, j, root.left);
        else if(i > m) return query(i, j, root.right);
        return query(i, m, root.left) + query(m + 1, j, root.right);
    }

    private void update(int index, int val, Node root) {
        if (root.i == index && root.j == index) {
            root.sum = val;
            return;
        }
        int m = root.i + (root.j - root.i) / 2;
        if (index <= m) update(index, val, root.left);
        else update(index, val, root.right);
        root.sum = root.left.sum + root.right.sum;
    }

    private Node buildTree(int i, int j, int[] nums) {
        if (i == j) return new Node(i, j, nums[i], null, null);
        int m = (i + j) / 2;
        Node left = buildTree(i, m, nums);
        Node right = buildTree(m + 1, j, nums);
        //System.out.println(i + " " + j + " " + (left.sum + right.sum));
        return new Node(i, j, left.sum + right.sum, left, right);
    }

}

class Node {
    Node left, right;
    int i, j, sum;

    public Node(int i, int j, int sum, Node left, Node right) {
        this.i = i;
        this.j = j;
        this.sum = sum;
        this.left = left;
        this.right = right;
    }
}