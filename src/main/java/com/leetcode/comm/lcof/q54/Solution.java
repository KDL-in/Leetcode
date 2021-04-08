package com.leetcode.comm.lcof.q54;

/*
* 230. Kth Smallest Element in a BST
* 二叉树中第k大的元素
* https://leetcode.com/problems/kth-smallest-element-in-a-bst/
*
* */
/*
中序遍历
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
    private int res = -1;
    
    public int kthSmallest(TreeNode root, int k) {
        trav(root, 0, k);
        return res;
    }
    
    private int trav(TreeNode root, int i, int k){
        if(root == null || i == k) return i;
        
        i = trav(root.left, i, k);
        i += 1;
        if(i == k) res = root.val;
        i = trav(root.right, i, k);
        
        return i;                                                                                                                                                                         
    }
}