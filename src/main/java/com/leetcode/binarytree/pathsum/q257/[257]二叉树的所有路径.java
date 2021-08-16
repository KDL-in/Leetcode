package com.leetcode.binarytree.pathsum.q257;//给定一个二叉树，返回所有从根节点到叶子节点的路径。
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 字符串 二叉树 
// 👍 556 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

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
    private List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        trav(root, new ArrayList<>());
        return res;
    }

    private void trav(TreeNode root, List <Integer>cur) {
        if (root == null) return;
        cur.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder str = new StringBuilder();
            for (Integer i : cur) {
                if (str.length() == 0) str.append(i);
                else str.append("->" + i);
            }
            res.add(str.toString());
        }
        trav(root.left, cur);
        trav(root.right, cur);
        cur.remove(cur.size() - 1);
    }
}
class SolutionV2 {
    private List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        trav(root, "");
        return res;
    }

    private void trav(TreeNode root, String path) {
        if (root == null) return;
        path += root.val;
        if (root.left == null && root.right == null){
            res.add(path);
        }
        trav(root.left, path + "->");
        trav(root.right, path + "->");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
