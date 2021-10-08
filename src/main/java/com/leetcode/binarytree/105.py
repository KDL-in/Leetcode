# 从前序和中序排列重构二叉树
# Given preorder and inorder traversal of a tree, construct the binary tree.

# Note:
# You may assume that duplicates do not exist in the tree.

# For example, given

# preorder = [3,9,20,15,7]
# inorder = [9,3,15,20,7]
# Return the following binary tree:

#     3
#    / \
#   9  20
#     /  \
#    15   7
import sys
sys.path.append("..")
from common.bintree import Node as TreeNode
from common.bintree import level_trav
# version 1
# 递归重构树，唯一问题就是下标细节
# 算法复杂度
# 时间，每层时间和N，深度最多N，一般情况是logN，所以最差N^2，一般情况NlogN
# 额外空间，无
# Runtime: 280 ms, faster than 16.21% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
# Memory Usage: 19.1 MB, less than 63.90% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, preorder, inorder):

        def rebuild(pl, ph, il, ih):
            if pl >= ph: return None
            cur = preorder[pl]
            # search in inorder
            for idx in range(il, ih):
                if inorder[idx] == cur: break
            n_left = idx-il # 左子树节点数
            left = rebuild(pl+1, pl+1+n_left, il, idx)
            right = rebuild(pl+1+n_left, ph, idx+1, ih)
            return TreeNode(val=cur, left=left, right=right)
        return rebuild(0, len(preorder), 0, len(inorder))
# version 2
# 空间换时间
# impl trick: TreeNode(val=cur, left=left, right=right)初始化同时递归会快一点
# Runtime: 48 ms, faster than 81.13% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
# Memory Usage: 19.4 MB, less than 62.88% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, preorder, inorder):
        num2idx = dict(zip(inorder,range(len(inorder))))
        def rebuild(pl, ph, il, ih):
            if pl >= ph: return None
            cur = preorder[pl]
            # search in inorder
            idx = num2idx[cur]
            n_left = idx-il # 左子树节点数
            left = rebuild(pl+1, pl+1+n_left, il, idx)
            right = rebuild(pl+1+n_left, ph, idx+1, ih)
            return TreeNode(val=cur, left=left, right=right)
        return rebuild(0, len(preorder), 0, len(inorder))

# version 3
# 严格O(N)的算法
# Runtime: 40 ms, faster than 96.16% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
# Memory Usage: 17.8 MB, less than 93.03% of Python online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, preorder, inorder):
        def rebuild(stop):
            if inorder[-1] != stop:
                root = TreeNode(val=preorder.pop())
                root.left = rebuild(root.val)
                inorder.pop()
                root.right = rebuild(stop)
                return root
        preorder.reverse()
        inorder.append(None)
        inorder.reverse()
        
        return rebuild(None)
s = com.leetcode.dp.q91.Solution()
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
tree = s.buildTree(preorder, inorder)
result = []
level_trav(tree, result)