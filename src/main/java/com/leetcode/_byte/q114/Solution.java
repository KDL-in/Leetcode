package com.leetcode._byte.q114;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public void flatten(TreeNode root) {
        pretrav(root);
    }

    public TreeNode pretrav(TreeNode root){
        if (root == null) return null;
        TreeNode left = pretrav(root.left);
        TreeNode right = pretrav(root.right);
        root.left = null;
        root.right = left;
        TreeNode cur = root;
        while(cur.right != null) cur = cur.right;
        cur.right = right;
        return root;
    }
}