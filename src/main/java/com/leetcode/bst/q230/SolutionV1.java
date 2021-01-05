package com.leetcode.bst.q230;
/**
 * 230. Kth Smallest Element in a BST
 * 二分搜索树，第k大的元素
 *
 */

import com.leetcode.common.bintree.TreeNode;

import java.util.ArrayList;
/*
* 二叉搜索树中序遍历是有序的
*
* Runtime: 48 ms, faster than 78.16% of Python3 online submissions for Kth Smallest Element in a BST.
* Memory Usage: 18 MB, less than 67.18% of Python3 online submissions for Kth Smallest Element in a BST.
* */
public class SolutionV1 {
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inTrav(root, k, inorder);
        return inorder.get(k - 1);
    }

    private void inTrav(TreeNode root, int k, ArrayList<Integer> inorder) {
        if (root == null) {
            return;
        }
        inTrav(root.left, k, inorder);
        inorder.add(root.val);
        inTrav(root.right, k, inorder);
    }


}
