package com.leetcode.bst.q450;

import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;

/*
* 450. Delete Node in a BST
* BST的删除操作
* https://leetcode.com/problems/delete-node-in-a-bst/
*
* */

/*
最简单的bst删除，查找，寻找后继节点，删除
3个cases
1. 左右子树为空，直接删除
2. 左右子树一个为空，替代之
3. 左右子树不为空，寻找右子树的最左节点，即中序遍历的第一个后继，交换，递归删除掉
Runtime: 0 ms, faster than 100.00% of Java online submissions for Delete Node in a BST.
Memory Usage: 39.4 MB, less than 59.56% of Java online submissions for Delete Node in a BST.
* * */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) root.left = deleteNode(root.left, key);

        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else{// key == root val
            if (root.left == null && root.right == null) root = null;
            else if (root.left != null && root.right != null) {
                TreeNode successor = succ(root.right);
                int t = successor.val;
                successor.val = root.val;
                root.val = t;
                root.right = deleteNode(root.right, key);
            } else {
                root = root.left == null ? root.right : root.left;
            }
        }
        return root;
    }

    private TreeNode succ(TreeNode cur) {
        while (cur.left != null) { cur = cur.left; }
        return cur;
    }

    public static void main(String[] args) {
        String input = "5,3,6,2,4,null,7";
        TreeNode root = BinTreeTools.levRebuild(input);
        BinTreeTools.trav(root, "lev");
        TreeNode newRoot = new Solution().deleteNode(root, 3);
        BinTreeTools.trav(newRoot, "lev");
    }
}
