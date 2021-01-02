""" 
==========================================
Task:

114. Flatten Binary Tree to Linked List
原地平摊二叉树

==========================================
Notes:



==========================================
Examples:



===========================================
"""

# version 1
# 递归，只看当前节点（普遍节点）
# Runtime: 20 ms, faster than 93.24% of Python online submissions for Flatten Binary Tree to Linked List.
# Memory Usage: 14.4 MB, less than 59.05% of Python online submissions for Flatten Binary Tree to Linked List.
class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        def flatten(root):
            if root:
                flatten(root.left)
                flatten(root.right)
                left, right = root.left, root.right
                root.left,root.right  = None, left
                while root.right:
                    root = root.right
                root.right = right
        flatten(root)



# version 2
# 暴力的先序遍历
# Runtime: 24 ms
# Memory Usage: 14.3 MB
class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        nodes = []
        def trav(root):
            if root:
                nodes.append(root)
                trav(root.left)
                trav(root.right)
        trav(root)
        pre = nodes[0]
        for i in range(1, len(nodes)):
            pre.left = None
            pre.right = nodes[i]
            pre = nodes[i]
            


