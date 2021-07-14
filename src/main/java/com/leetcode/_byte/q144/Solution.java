package com.leetcode._byte.q144;

/*
* 144. Binary Tree Preorder Traversal
* 前序遍历
* https://leetcode.com/problems/binary-tree-preorder-traversal/
* */

import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<Integer>();
        trav(root);
        return res;
    }
    
    private void trav(TreeNode root){
        if(root == null) return;
        res.add(root.val);
        trav(root.left);
        trav(root.right);
    }
}