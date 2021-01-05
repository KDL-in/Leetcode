# 230. Kth Smallest Element in a BST
# 查找二叉树搜索第k大的元素
# Medium

# 3342

# 79

# Add to List

# Share
# Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 

# Example 1:

# Input: root = [3,1,4,null,2], k = 1
#    3
#   / \
#  1   4
#   \
#    2
# Output: 1
# Example 2:

# Input: root = [5,3,6,2,4,null,null,1], k = 3
#        5
#       / \
#      3   6
#     / \
#    2   4
#   /
#  1
# Output: 3
# Follow up:
# What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

 

# Constraints:

# The number of elements of the BST is between 1 to 10^4.
# You may assume k is always valid, 1 ≤ k ≤ BST's total elements.


""" 
version 1
二叉搜索树中序遍历是有序的
优化，剪枝
时间复杂度，等于访问节点的数量，O(N)
-
Runtime: 48 ms, faster than 44.44% of Python online submissions for Kth Smallest Element in a BST.
Memory Usage: 21.3 MB, less than 13.67% of Python online submissions for Kth Smallest Element in a BST.
"""
import sys
sys.path.append("..")
from common.bintree import Node as TreeNode
from common.bintree import trav,lev_rebuild
import collections
from timeit import timeit
class Solution(object):
    def kthSmallest(self, root, k):
        """
        :type root: TreeNode
        :type k: int
        :rtype: int
        """
        inorder = []
        def in_trav(root):
            if root:
                in_trav(root.left)
                inorder.append(root.val)
                in_trav(root.right)
        in_trav(root)
        return inorder[k-1]

class Solution(object):
    
    def kthSmallest(self, root, k):
        """
        :type root: TreeNode
        :type k: int
        :rtype: int
        """
        i, r = 0,0
        def in_trav(root):
            nonlocal i,r
            if not r and root:
                in_trav(root.left)
                i += 1
                if i == k:
                    r = root.val
                    return
                in_trav(root.right)
        in_trav(root)
        return r


root = [5,13,6,2,4,'null','null',1]
root = lev_rebuild(root)
s = Solution()
s.kthSmallest(root, 3)

""" 
性能测试
从运行结果来看，剪枝还是非常有用的，尽管在提交中并没有体现，只是因为它的case太少。
完全二叉树节点数随高度指数型增长，每次翻倍，因而version1.1的时间也在翻倍，而version1.2搜索节点为二分之一，符合预期。

"""

i = 0
def tree(height):
    global i
    if height:
        root = TreeNode(val=i)
        i += 1
        root.left = tree(height-1)
        root.right = tree(height-1)
        return root

func = Solution().kthSmallest
for n in range(5,12):
    i = 0
    root = tree(n)
    print(i, timeit(lambda :func(root,int(i/2)),number=100000))


# version 1
# 1
# 5 0.7806704999999999
# 6 1.5313236999999997
# 7 3.0275512999999994
# 8 6.022758700000001
# 9 11.8819812
# 10 23.840767899999996
# 2
# 31 0.4433974999999464
# 63 0.8129605999999967
# 127 1.6133743999999979
# 255 3.1359549000000015
# 511 6.211461299999996
# 1023 12.923811200000046