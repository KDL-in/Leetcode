package q199;

import java.util.*;
/*
*
* 199. Binary Tree Right Side View
* 二叉树的右侧视图
* https://leetcode.com/problems/binary-tree-right-side-view/
* */
/*
没有想象的那么简单，需要用层次遍历，找每一层最后一个
* */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root==null) return list;
        TreeNode cur = null;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            list.add(cur.val);
        }
        return list;
    }
}