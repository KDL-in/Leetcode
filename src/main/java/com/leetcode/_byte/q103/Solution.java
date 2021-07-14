package q103;

import java.util.*;


/*
* 103. Binary Tree Zigzag Level Order Traversal
* 二叉树，锯齿遍历
* https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
* */
/*
用一个队列首尾地放
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        TreeNode cur;
        boolean flag = true;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> tlist = new ArrayList<Integer>();

            if (flag) {
                for (int i = 0; i < size; i++) {
                    cur = q.removeFirst();
                    //System.out.println(cur.val);
                    tlist.add(cur.val);
                    if (cur.left != null) q.addLast(cur.left);
                    if (cur.right != null) q.addLast(cur.right);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    cur = q.removeLast();
                    //System.out.println(cur.val);
                    tlist.add(cur.val);
                    if (cur.right != null) q.addFirst(cur.right);
                    if (cur.left != null) q.addFirst(cur.left);

                }
            }
            flag = !flag;
            res.add(tlist);
            System.out.println(size);
        }
        return res;
    }
}