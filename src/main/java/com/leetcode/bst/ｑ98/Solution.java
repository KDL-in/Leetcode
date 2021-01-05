package com.leetcode.bst.ｑ98;
/*
* 98. Validate Binary Search Tree
* bst合法性检查，返回true or false
* https://leetcode.com/problems/validate-binary-search-tree/
* */
import com.leetcode.common.bintree.TreeNode;
// 递归检查合法性，当这个看起来简单其实不好写
// 这个递归对于左右子树的要求是不同的，min和max也对应着不同的含义
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
// Memory Usage: 38.9 MB, less than 36.06% of Java online submissions for Validate Binary Search Tree.
class Solution {
    public boolean isValidBST(TreeNode root) {
        return checkBST(root, null, null);
    }

    private boolean checkBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        // 对于当前节点，接收到min和max，如果min和max存在，则必须满足 min < cur < max
        boolean flag = true;
        if (min != null) flag = flag && root.val > min.val;
        if (max != null) flag = flag && root.val < max.val;
        // 其次，它对它左右子树有不同的要求
        // 对于左子树，它要求左子树不得高于但前值，如果当前是父节点的右子树，同时必须大于父节点传来的min值
        // 对于右子树，它要求右子树不得小于当前值，如果当前是父节点的左子树，则同时必须小于父节点的max值
        return flag && checkBST(root.left, min, root) && checkBST(root.right, root, max);
    }
}