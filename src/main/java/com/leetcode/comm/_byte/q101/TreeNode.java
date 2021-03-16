package com.leetcode.comm._byte.q101;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
// 内存用尽
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
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.val != 101) {
                    q.offer(cur.left == null ? new TreeNode(-101) : cur.left);
                    q.offer(cur.right == null ? new TreeNode(-101) : cur.right);
                }

                if (cur == root) continue;
                if (i < size / 2) {
                    stack.push(cur.val);
                } else {
                    if (stack.peek()==cur.val) stack.pop();
                    else return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(null);
    }
}