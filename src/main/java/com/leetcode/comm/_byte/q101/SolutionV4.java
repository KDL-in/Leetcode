package com.leetcode.comm._byte.q101;
/*
来自官方题解

递归还能这么写
* */
class SolutionV4 {
    public boolean isSymmetric(TreeNode root) {
        return trav(root, root);
    }

    private boolean trav(TreeNode l, TreeNode r){
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return (l.val == r.val) && trav(l.left, r.right) && trav(l.right, r.left);

    }
}