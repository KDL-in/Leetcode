package com.leetcode.comm.lcof._q26;


/*
572. Subtree of Another Tree

https://leetcode.com/problems/subtree-of-another-tree/

这里另一种更好的递归实现.对于检查是否存在重复子树这件事,其实主要任务如下:

1. 检查以当前节点未根节点的子树是否相等(此处嵌套递归)
2. 检查s的左子树中是否有该子树t
3. 检查s的右子树中是否有该子树t

这里使用了嵌套递归实现.
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

class SolutionV2 {
    private boolean flag;
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (flag) return flag;
        if(s == null) return false;
        flag = checkCT(s, t);
        if(!flag) flag = isSubtree(s.left, t);
        if(!flag) flag = isSubtree(s.right, t);
        
        return flag;
    }
    private boolean checkCT(TreeNode s, TreeNode t){
        if(s == null && t ==null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && checkCT(s.left, t.left) && checkCT(s.right, t.right);
    }

}