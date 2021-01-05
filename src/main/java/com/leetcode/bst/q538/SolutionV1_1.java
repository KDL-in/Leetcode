package com.leetcode.bst.q538;

import com.leetcode.common.bintree.TreeNode;
/*
* 全局变量版本，更好写
* Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert BST to Greater Tree.
* Memory Usage: 39.2 MB, less than 67.29% of Java online submissions for Convert BST to Greater Tree.
* */
public class SolutionV1_1 {
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        bstToGreaterTree(root);
        return root;
    }

    private void bstToGreaterTree(TreeNode root) {
        if (root == null) return;
        bstToGreaterTree(root.right);
        sum += root.val;
        root.val = sum;
        bstToGreaterTree(root.left);
        return;
    }
}
