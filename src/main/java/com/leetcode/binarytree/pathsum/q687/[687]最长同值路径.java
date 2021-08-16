package com.leetcode.binarytree.pathsum.q687;//给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
//
// 注意：两个节点之间的路径长度由它们之间的边数表示。 
//
// 示例 1: 
//
// 输入: 
//
// 
//              5
//             / \
//            4   5
//           / \   \
//          1   1   5
// 
//
// 输出: 
//
// 
//2
// 
//
// 示例 2: 
//
// 输入: 
//
// 
//              1
//             / \
//            4   5
//           / \   \
//          4   4   5
// 
//
// 输出: 
//
// 
//2
// 
//
// 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 483 👎 0


import com.leetcode.common.bintree.TreeNode;
//leetcode submit region begin(Prohibit modification and deletion)


/**
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
/*
同样视为无向图路径。
每次递归，left = trav(root.left)，返回左子树中与root直接相连的最长同值路径
 */
class Solution {
    private int res;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        res = 1;
        trav(root);
        return res - 1;
    }

    private int trav(TreeNode root) {
        int cur = 1;
        if (root.left != null) {
            int l = trav(root.left);
            if (root.left.val == root.val) {
                cur = l + 1;
                res = Math.max(cur, res);
            }
        }
        if (root.right != null) {
            int r = trav(root.right);
            if (root.right.val == root.val) {
                // 两种可能，1，root.left.val ！= root.val，则res = Math.max(1 + r, res)
                // 2, root.left.val == root.val, 则res = Math.max(l + 1 + r, res);
                res = Math.max(cur + r, res);
                cur = Math.max(cur, r + 1);
            }
        }

        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
