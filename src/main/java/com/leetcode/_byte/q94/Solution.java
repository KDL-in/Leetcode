package com.leetcode._byte.q94;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
* 94. Binary Tree Inorder Traversal
* 中序遍历
* https://leetcode.com/problems/binary-tree-inorder-traversal/
* */
/*
尝试用stack做中序遍历
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

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null){
            //核心，记住入栈条件，其他都可以推导
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

}