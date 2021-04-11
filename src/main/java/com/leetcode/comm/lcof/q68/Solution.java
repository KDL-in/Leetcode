package com.leetcode.comm.lcof.q68;

/*
* 235. Lowest Common Ancestor of a Binary Search Tree
* 二叉树的公共祖先
* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
* */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        if(left != null) return left;
        if(right != null) return right;
        return null;
    }
}