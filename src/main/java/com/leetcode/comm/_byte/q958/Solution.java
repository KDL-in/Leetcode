package com.leetcode.comm._byte.q958;


import java.util.ArrayDeque;
import java.util.Queue;
/*
* 958. Check Completeness of a Binary Tree
* 完全二叉树的完整性
* https://leetcode.com/problems/check-completeness-of-a-binary-tree/
* */

/*
利用层次遍历，核心是，当遍历到第一次子节点为空之后，之后的所有遍历不应该再出现字节点。
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
    public boolean isCompleteTree(TreeNode root) {
        boolean flag = false;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; ++i){
                TreeNode cur = q.poll();
                if(cur.left != null){
                    if(flag) return false;
                    q.offer(cur.left);
                } else flag = true;
                if(cur.right != null){
                    if(flag) return false;
                    q.offer(cur.right);
                } else flag = true;
            }
        }
        return true;
    }
}