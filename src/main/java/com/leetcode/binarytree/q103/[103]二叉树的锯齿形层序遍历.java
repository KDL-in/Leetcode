package com.leetcode.binarytree.q103;//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层序遍历如下： 
//
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 二叉树 👍 497 👎 0


import com.leetcode.common.bintree.TreeNode;

import java.util.*;
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
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> q = new ArrayList<>();
        if (root != null)q.add(root);
        boolean flag = false;
        while (!q.isEmpty()) {
            List<Integer> curList = new ArrayList<>();
            List<TreeNode> nextQ = new ArrayList<>();
            for (int i = q.size() - 1; i >= 0; i --) {
                TreeNode cur = q.get(i);
                curList.add(cur.val);
                trav(nextQ, cur, flag);
            }
            flag = !flag;
            res.add(curList);
            q = nextQ;
        }
        return res;
    }

    private void trav(List<TreeNode> q, TreeNode cur, boolean flag) {
        if (flag) {
            if (cur.right != null)q.add(cur.right);
            if (cur.left != null)q.add(cur.left);
        } else {
            if (cur.left != null)q.add(cur.left);
            if (cur.right != null)q.add(cur.right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
