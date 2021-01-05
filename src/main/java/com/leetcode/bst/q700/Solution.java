package com.leetcode.bst.q700;

import com.leetcode.common.bintree.TreeNode;

/*
* 700. Search in a Binary Search Tree
* bst搜索，返回节点
* https://leetcode.com/problems/search-in-a-binary-search-tree/
* */

//Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in a Binary Search Tree.
// Memory Usage: 39.6 MB, less than 49.57% of Java online submissions for Search in a Binary Search Tree.
public class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) return searchBST(root.left, val);
        else if (val > root.val) return searchBST(root.right, val);
        else return root;
    }
}
