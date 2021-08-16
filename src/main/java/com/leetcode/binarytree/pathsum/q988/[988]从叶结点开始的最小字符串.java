package com.leetcode.binarytree.pathsum.q988;//给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表
//'b'，依此类推。 
//
// 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。 
//
// （小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。） 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,1,2,3,4,3,4]
//输出："dba"
// 
//
// 示例 2： 
//
// 
//
// 输入：[25,1,3,1,3,0,2]
//输出："adz"
// 
//
// 示例 3： 
//
// 
//
// 输入：[2,2,1,null,1,0,null,0]
//输出："abc"
// 
//
// 
//
// 提示： 
//
// 
// 给定树的结点数介于 1 和 8500 之间。 
// 树中的每个结点都有一个介于 0 和 25 之间的值。 
// 
// Related Topics 树 深度优先搜索 字符串 二叉树 
// 👍 60 👎 0


import com.leetcode.common.bintree.TreeNode;
//leetcode submit region begin(Prohibit modification and deletion)


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private String res = (char) ('z' + 1) + "";

    public String smallestFromLeaf(TreeNode root) {
        trav(root, new StringBuilder());
        return res;
    }

    private void trav(TreeNode root, StringBuilder cur) {
        if (root == null) return;
        cur.append((char)(root.val + 'a'));
        //System.out.println(cur.toString());
        if (root.left == root.right) {
            String t = cur.reverse().toString();
            if (t.compareTo(res) < 0) res = t;
            cur.reverse();
        }
        trav(root.left, cur);
        trav(root.right, cur);
        cur.setLength(cur.length() - 1);
    }
}
class SolutionV2 {
    private String res = (char) ('z' + 1) + "";

    public String smallestFromLeaf(TreeNode root) {
        trav(root, "");
        return res;
    }

    private void trav(TreeNode root, String cur) {
        if (root == null) return;
        cur = (char)('a' + root.val) + cur;
//        System.out.println(cur);
        if (root.left == root.right && cur.compareTo(res) < 0) {
            res = cur;
            return;
        }
        trav(root.left, cur);
        trav(root.right, cur);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
