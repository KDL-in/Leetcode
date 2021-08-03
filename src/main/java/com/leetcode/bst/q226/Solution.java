package com.leetcode.bst.q226;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    private static void trav(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        trav(root.left);
        trav(root.right);
    }
    private static TreeNode buildTree(int i, int[] arr) {
        if (i > arr.length) return null;

        TreeNode root = new TreeNode(arr[i-1]);
        root.left = buildTree(i*2, arr);
        root.right = buildTree(i*2+1, arr);
        return root;
    }
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        TreeNode root = buildTree(1, arr);
        trav(root);
        System.out.println();
        new Solution().invertTree(root);
        trav(root);
    }

}
