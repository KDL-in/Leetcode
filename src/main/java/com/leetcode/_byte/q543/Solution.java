package com.leetcode._byte.q543;

/*
* 543. Diameter of Binary Tree
* 二叉树的直径
* https://leetcode.com/problems/diameter-of-binary-tree/
*
* */
/*
很像二叉树的最长路径和，需要借助返回值才好做
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
    private int d = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        trav(root);
        return d-1;
    }
    
    private int trav(TreeNode root){
        if(root == null) return 0;
        int left = trav(root.left);
        int right = trav(root.right);
        d = Math.max(right + left + 1, d);
        return Math.max(left, right) + 1;
    }
    
}