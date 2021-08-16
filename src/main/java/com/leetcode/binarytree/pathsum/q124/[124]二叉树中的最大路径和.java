package com.leetcode.binarytree.pathsum.q124;//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 104] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 
// 👍 1156 👎 0


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
题目其实将二叉树看作无向图，要求其中任意路径的最大和，节点至少一个。
但这道题解应该看作二叉树，对于任意一个局部，所要求返回的是
Math.max(Math.max(left, right) + root.val, root.val)
所要求的是max(左边，右边，左边+cur，右边+cur，cur，左边+右边+cur)
 */
class Solution {
    private int max;

    public int maxPathSum(TreeNode root) {
        max = root.val;
        trav(root);
        return max;
    }

    private int trav(TreeNode root) {
        if (root == null) return -1001;
        int left = trav(root.left);
        int right = trav(root.right);
        // max来源于左边，右边，左边+cur，右边+cur，cur，左边+右边+cur
        if (root.val > 0) max = Math.max(Math.max(left, right) + root.val, max);
        else max = Math.max(Math.max(left, right), max);
        max = Math.max(max, root.val);
        max = Math.max(max, left + right + root.val);
        return Math.max(Math.max(left, right) + root.val, root.val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
