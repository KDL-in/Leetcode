""" 
==========================================
Task:

226. Invert Binary Tree
翻转二叉树
https://leetcode.com/problems/invert-binary-tree/

==========================================
Notes:

==========================================
Examples:

===========================================
"""
# verion 1
# ……
# Runtime: 24 ms, faster than 23.57% of Python online submissions for Invert Binary Tree.
# Memory Usage: 13.3 MB, less than 81.53% of Python online submissions for Invert Binary Tree.
class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        def rev(root):
            if root is None: return
            t = root.left
            root.left = root.right
            root.right = t
            rev(root.left)
            rev(root.right)
        rev(root)
        return root

# version 2
# pythonic
# a, b = b, a的力量
# Runtime: 12 ms, faster than 95.93% of Python online submissions for Invert Binary Tree.
# Memory Usage: 13.5 MB, less than 55.22% of Python online submissions for Invert Binary Tree.
class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        def rev(root):
            if root:
                root.right, root.left = rev(root.left), rev(root.right)
                return root
        return rev(root)

class Solution(object):
    def invertTree(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        if root:
            root.right, root.left = self.invertTree(root.left), self.invertTree(root.right)
            return root