package com.leetcode.comm.q110;

import java.util.HashSet;

/*
* 110. Balanced Binary Tree
* 判断一棵树是否是二叉树
* https://leetcode.com/problems/balanced-binary-tree/
*
* */
/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Balanced Binary Tree.
Memory Usage: 39.1 MB, less than 62.27% of Java online submissions for Balanced Binary Tree.
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
    public boolean isBalanced(TreeNode root) {
        return trav(root) >= 0;
    }
    
    private int trav(TreeNode root){
        if(root == null) return 0;
        
        int left = trav(root.left);
        int right = trav(root.right);
        //System.out.println(root.val + " " + left + " " + right);
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        
        return Math.max(left,  right) + 1;
    }
}