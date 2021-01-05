package com.leetcode.bst.q538;

import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
* 迭代版本
* Runtime: 2 ms, faster than 17.80% of Java online submissions for Convert BST to Greater Tree.
* Memory Usage: 39.2 MB, less than 79.53% of Java online submissions for Convert BST to Greater Tree.
* */
public class SolutionV2 {
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode r = root;
        int sum = 0;
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }
            if (stack.isEmpty()) return r;
            root = stack.peek();
            stack.pop();
            sum += root.val;
            root.val = sum;
            root = root.left;
        }
    }


    public static void main(String[] args) {
        String input = "4,1,6,0,2,5,7,null,null,null,3,null,null,null,8";
        TreeNode inputRoot = BinTreeTools.levRebuild(input);
        BinTreeTools.trav(inputRoot, "lev");
        TreeNode root = new SolutionV2().convertBST(inputRoot);
        BinTreeTools.trav(root, "lev");
    }
}
