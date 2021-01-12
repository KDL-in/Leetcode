package com.leetcode.common.bintree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.leetcode.common.InputTools.inputSplit;

public class BinTreeTools {

    public static List<TreeNode> trav(TreeNode root, String mode) {
        List<TreeNode> list = new ArrayList<>();
        if (mode.equals("lev"))
            lev_trav(root, list);
        for (TreeNode treeNode : list) {
            System.out.print(treeNode+" ");
        }
        System.out.println();
        return list;
    }
    //"4,1,6,0,2,5,7,null,null,null,3,null,null,null,8";
    private static void lev_trav(TreeNode root, List<TreeNode> r) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                r.add(cur);
                if (cur != null) queue.offer(cur.left);
                if (cur != null)queue.offer(cur.right);
            }
        }
    }
    public static TreeNode levRebuild(String input) {
        // 解析输入
        List<Integer> data = inputSplit(input, ",");
        int i = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(data.get(i++));
        queue.offer(root);

        // 重构树
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode cur = queue.poll();
                if (i >= data.size()) break;
                Integer curVal = data.get(i++);
                if (curVal != null) {
                    cur.left = new TreeNode(curVal);
                    queue.offer(cur.left);
                }
                if (i >= data.size()) break;
                Integer curVal2 = data.get(i++);
                if (curVal2 != null) {
                    cur.right = new TreeNode(curVal2);
                    queue.offer(cur.right);
                }
            }
        }

        return root;
    }



}
