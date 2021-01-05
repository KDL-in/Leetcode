package com.leetcode.bst.q538;

import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;

/*
*
* Runtime: 3 ms, faster than 93.00% of Java online submissions for Convert BST to Greater Tree.
* Memory Usage: 39.2 MB, less than 67.29% of Java online submissions for Convert BST to Greater Tree.
* */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        System.out.println(bstToGreaterTree(root,0));
        return root;
    }

    private int bstToGreaterTree(TreeNode root, int parSum) {
        if (root == null) return parSum;
        int rightSum = bstToGreaterTree(root.right, parSum);
        root.val += rightSum;
        int leftSum = bstToGreaterTree(root.left, root.val);
        return leftSum;
    }

    public static void main(String[] args) {
        String input = "4,1,6,0,2,5,7,null,null,null,3,null,null,null,8";
        TreeNode inputRoot = BinTreeTools.levRebuild(input);
        BinTreeTools.trav(inputRoot, "lev");
        TreeNode root = new Solution().convertBST(inputRoot);
        BinTreeTools.trav(root, "lev");
    }
}