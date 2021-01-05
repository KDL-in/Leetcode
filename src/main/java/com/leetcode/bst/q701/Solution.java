package com.leetcode.bst.q701;
/*
* 701. Insert into a Binary Search Tree
* bst插入节点
* https://leetcode.com/problems/insert-into-a-binary-search-tree/
* */


import com.leetcode.common.bintree.TreeNode;

// 主要是回溯 +　赋值很简洁
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Insert into a Binary Search Tree.
// Memory Usage: 39.7 MB, less than 67.96% of Java online submissions for Insert into a Binary Search Tree.
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else if (val > root.val) root.right = insertIntoBST(root.right, val);
        return root;
    }
}