package com.leetcode.bst.ｑ98;


import com.leetcode.common.bintree.TreeNode;
/*
这是一种更优雅的解法，非常好理解
BST的约束——左子树都小于cur，右子树都大于cur。这个约束并没有看上去那么简单，事实上对于每个节点来说
它对左子树的要求是，必须限制在一个范围内 min < all in left < cur.val，这个min并不是什么时候都存在，只有在当前节点是父节点的右子树时，它的mean来自于父节点的cur.val
他对右子树的要求是，必须限制在一个范围内 cur.val < all in right < max, 这个max并不是什么时候都存在，只有在当前节点是父节点的左子树时，它的max来自父节点的cur.val
https://leetcode.com/problems/validate-binary-search-tree/discuss/32109/My-simple-Java-solution-in-3-lines
*/
public class SolutionV2 {    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
