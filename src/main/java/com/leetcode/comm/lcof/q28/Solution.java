package com.leetcode.comm.lcof.q28;

/*
226. Invert Binary Tree
二叉树翻转
https://leetcode.com/problems/invert-binary-tree/
* */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode t = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(t);
        return root;
    }
}