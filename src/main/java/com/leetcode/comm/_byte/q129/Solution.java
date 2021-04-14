package com.leetcode.comm._byte.q129;

/*
*
* 129. Sum Root to Leaf Numbers
* 二叉树的和，二，提前试探的遍历
* https://leetcode.com/problems/sum-root-to-leaf-numbers/
*
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
    private int sum = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        trav(root, 0);
        return sum;
    }
    
    private void trav(TreeNode root, int curSum){
        
        curSum = curSum * 10 + root.val;
        if(root.left == null && root.right == null){
            sum += curSum;
            return;
        }
        if(root.left != null) trav(root.left, curSum);
        if(root.right != null) trav(root.right, curSum);
    }
}