package com.leetcode.bst.q230;

import com.leetcode.common.bintree.TreeNode;

import java.util.ArrayList;
/*
* 剪枝
* Runtime: 0 ms, faster than 100.00% of Java online submissions for Kth Smallest Element in a BST.
* Memory Usage: 38.9 MB, less than 55.02% of Java online submissions for Kth Smallest Element in a BST.
* */
public class SolutionV1_1 {
    private int r, i;
    public int kthSmallest(TreeNode root, int k) {
        inTrav(root,k);
        return r;
    }

    private void inTrav(TreeNode root, int k) {
        if (r == 0 && root != null) {
            inTrav(root.left, k);
            i++;
            if (i == k) {
                r = root.val;
            }
            inTrav(root.right, k);
        }
    }
}
