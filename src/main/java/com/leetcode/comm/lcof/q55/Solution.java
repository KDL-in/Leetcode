package com.leetcode.comm.lcof.q55;
/*
* 104. Maximum Depth of Binary Tree
* 最大深度
* https://leetcode.com/problems/maximum-depth-of-binary-tree/
*
* */

/*
普通树遍历
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
    private int max = 0;
    public int maxDepth(TreeNode root) {
        trav(root, 0);
        return max;
    }
    
    private void trav(TreeNode root, int depth){
        if(root == null) {
            max = Math.max(max, depth);
            return;
        }
        trav(root.left, depth + 1);
        trav(root.right, depth + 1);
    }
}