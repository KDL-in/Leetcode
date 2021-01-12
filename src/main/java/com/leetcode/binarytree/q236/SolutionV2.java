package com.leetcode.binarytree.q236;

import com.leetcode.common.bintree.TreeNode;

import java.util.HashMap;
import java.util.Map;


/*
 后序遍历可以解决该问题！重点在于如何去定义递归。该算法极为简洁
 - 基础可行保证，后序遍历，能够先获得左右子树的所有信息，从宏观来看是从底向上访问的，这至少有了解决该问题的基础
 - 正确地定义递归任务——在左右子树中查找目标节点。
   - 如果左子树右子树同时找到，那么直接返回root，作为LCA
   - 如果都找不到，那返回null
   - 如果找到一个，那返回该节点

算法复杂度
O(H)，事实上应该等于p，q的高度，最好的情况下要好于O(logN)，最差的情况下好于O(N)
空间复杂度
无额外引入

超级简洁的实现
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root;
}
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C%2B%2BJavaPythonRuby
 Runtime: 11 ms, faster than 10.32% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 Memory Usage: 45 MB, less than 6.83% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 ----
 Runtime: 5 ms, faster than 35.70% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 Memory Usage: 40.8 MB, less than 86.28% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 ----
 Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
 Memory Usage: 40.8 MB, less than 86.28% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
*/

public class SolutionV2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root, p, q);
    }

    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 找到其中一个节点，不需要往下递归
        if (root == p || root == q) return root;
        // 在左子树中查找
        TreeNode left = findLCA(root.left, p, q);
        // 在右子树中查找
        TreeNode right = findLCA(root.right, p, q);
        // 左右子树中找到目标p，q，说明root是p，q的LCA
        if (left != null && right != null) return root;
        // 都找不到，返回null
        else if (left == null && right == null) return null;
        // 找到一个，返回一个
        else return left == null ? right : left;
    }
}
