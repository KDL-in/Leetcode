package com.leetcode.binarytree.q297;

import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;

/*
* 297. Serialize and Deserialize Binary Tree
* 二叉树的序列化和反序列化
* https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
* */

// Runtime: 5 ms, faster than 97.98% of Java online submissions for Serialize and Deserialize Binary Tree.
//         Memory Usage: 40.7 MB, less than 78.10% of Java online submissions for Serialize and Deserialize Binary Tree.
public class Codec {
    public static final String SEP = ",";
    public static final String NULL = "#";
    StringBuilder sBuildr;
    int i;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sBuildr = new StringBuilder();
        serializeTree(root);
        return sBuildr.toString();
    }

    private void serializeTree(TreeNode root) {
        if (root == null) {
            sBuildr.append(NULL).append(SEP);
            return;
        }
        sBuildr.append(root.val).append(SEP);
        serializeTree(root.left);
        serializeTree(root.right);
    }

    // Decodes your encoded data to tree.
    // 1,2,#,#,3,4,#,#,5,#,#,
    public TreeNode deserialize(String data) {
        i = 0;
        return deserializeTree(data.split(SEP));
    }

    private TreeNode deserializeTree(String[] data) {
        if (i >= data.length || NULL.equals(data[i])){
            i++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(data[i++]));
        root.left = deserializeTree(data);
        root.right = deserializeTree(data);
        return root;
    }

    public static void main(String[] args) {
        String input = "1,2,3,null,null,4,5";
        TreeNode treeNode = BinTreeTools.levRebuild(input);
        BinTreeTools.trav(treeNode, "lev");
        String output = new Codec().serialize(treeNode);
        System.out.println(output);
        TreeNode deserialize = new Codec().deserialize(output);
        BinTreeTools.trav(deserialize, "lev");
    }
}