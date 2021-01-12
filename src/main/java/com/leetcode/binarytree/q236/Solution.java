package com.leetcode.binarytree.q236;

import com.leetcode.common.bintree.BinTreeTools;
import com.leetcode.common.bintree.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/*
* 236. Lowest Common Ancestor of a Binary Tree
* 节点的最小公共祖先获取
* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
*
* */


/*
思想是遍历记录父亲和树高，通过对比树高，分别向上查找父亲
时间复杂度：
trav ： O(N) * O(hash)，O(hash)期望为O(1)，所以事实上该操作的算法复杂度为O(N)
findLCA: O(H) * O(hash)，树高最差情况下是O(N)
空间复杂度：
引入了hashMap，开销O(N)
Runtime: 14 ms, faster than 6.88% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
      Memory Usage: 47.5 MB, less than 5.89% of Java online submissions for Lowest Common Ancestor of a Binary Tree.
*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, Point> par = new HashMap<>();
        trav(root, -1, root, par);

        return findLCA(p, q, par);
    }

    private TreeNode findLCA(TreeNode p, TreeNode q, Map<Integer, Point> par) {
        Point p1 = new Point(p,par.get(p.val).h+1), q1 = new Point(q,par.get(q.val).h+1);
        while (p1.v != q1.v) {
            if (p1.h > q1.h) p1 = par.get(p1.v.val);
            else if (p1.h < q1.h) q1 = par.get(q1.v.val);
            else {
                p1 = par.get(p1.v.val);
                q1 = par.get(q1.v.val);
            }
        }
        return p1.v;
    }


    private class Point {
        TreeNode v;
        int h;

        public Point(TreeNode v, int h) {
            this.v = v;
            this.h = h;
        }
    }
    private void trav(TreeNode root, int height, TreeNode p, Map<Integer, Point> par) {
        if (root != null) {
            par.put(root.val, new Point(p, height));
            trav(root.left, height + 1, root, par);
            trav(root.right, height + 1, root, par);
        }
    }

    public static void main(String[] args) {
        String input = "3,5,1,6,2,0,8,null,null,7,4";
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode root = BinTreeTools.levRebuild(input);

        new Solution().lowestCommonAncestor(root, p, q);
    }
}