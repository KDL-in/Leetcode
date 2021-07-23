package com.leetcode._byte.q662宽度; /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import com.leetcode.common.bintree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 利用节点的pos来计算宽度，只要记录每层最左的节点x，则宽度等于 pos - x + 1
 
  */
class Solution {

    private Map<Integer, Integer> map;
    private int ans = 0;

    public int widthOfBinaryTree(TreeNode root) {
        map = new HashMap<>();
        dfs(root, 1, 0);
        return ans;
    }

    private void dfs(TreeNode root, int pos, int depth){
        if (root == null) return;
        map.computeIfAbsent(depth, x->pos);
        ans = Math.max(ans, pos - map.get(depth) + 1);
        dfs(root.left, pos * 2, depth + 1);
        dfs(root.right, pos * 2 + 1, depth + 1);
    }
}