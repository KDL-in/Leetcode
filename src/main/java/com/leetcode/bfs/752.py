""" 
==========================================
Task:

111. Minimum Depth of Binary Tree
最小树高
https://leetcode.com/problems/n-queens/

==========================================
Notes:



==========================================
Examples:

Input: root = [3,9,20,null,null,15,7]
Output: 2

===========================================
"""


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque
class com.leetcode.dp.q91.Solution(object):
    def minDepth(self, root):
        q = deque([(root, 1)])
        while q:
            cur,d = q.popleft()
            if cur.left == cur.right: return d
            cur.left and q.append((cur.left, d+1)), cur.right and q.append((cur.right, d+1))





s = com.leetcode.dp.q91.Solution()
s.solveNQueens(4)
