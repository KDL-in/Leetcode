package com.leetcode.bst.q700;

import com.leetcode.common.bintree.TreeNode;

// 迭代实现, 递归的空间消耗居然不高
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in a Binary Search Tree.
// Memory Usage: 39.8 MB, less than 20.05% of Java online submissions for Search in a Binary Search Tree.
public class SolutionV2 {

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val > root.val ? root.right : root.left;
        }
        return root;
    }

}
