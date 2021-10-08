# 中后序序列重构树
# 106. Construct Binary Tree from Inorder and Postorder Traversal
# Medium

# 2312

# 42

# Add to List

# Share
# Given inorder and postorder traversal of a tree, construct the binary tree.

# Note:
# You may assume that duplicates do not exist in the tree.

# For example, given

# inorder = [9,3,15,20,7]
# postorder = [9,15,7,20,3]
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
# 递归构造
# Runtime: 356 ms, faster than 10.68% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
# Memory Usage: 18.7 MB, less than 60.97% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, inorder, postorder):
        def rebuild(pl, ph, il, ih):
            if il >= ih: return None
            root = TreeNode(val = postorder[ph-1])
            # search in indorder
            for idx in range(il, ih):
                if inorder[idx] == root.val: break
            mid = ph-1-(ih-1-idx)
            root.right = rebuild(mid, ph-1, idx+1, ih)
            root.left = rebuild(pl, mid, il, idx)
            return root
        return rebuild(0, len(inorder), 0, len(inorder))
    
# version 2
# 空间换时间
# Runtime: 52 ms, faster than 66.41% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
# Memory Usage: 19.4 MB, less than 53.98% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, inorder, postorder):
        num2idx = dict(zip(inorder, range(len(inorder))))
        def rebuild(pl, ph, il, ih):
            if il >= ih: return None
            root = TreeNode(val = postorder[ph-1])
            # search in indorder
            idx = num2idx[root.val]
            mid = ph-1-(ih-1-idx)
            root.right = rebuild(mid, ph-1, idx+1, ih)
            root.left = rebuild(pl, mid, il, idx)
            return root
        return rebuild(0, len(inorder), 0, len(inorder))


# version  3 O（n）的算法
# Runtime: 32 ms, faster than 99.61% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
# Memory Usage: 17.7 MB, less than 94.95% of Python online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
class com.leetcode.dp.q91.Solution(object):
    def buildTree(self, inorder, postorder):
        # 告知stop，从二个数组重构整颗树，初始的stop为None
        def rebuild(stop):
            if  inorder[-1] != stop:
                # 新建当前节点
                root  = TreeNode(val=postorder.pop())
                # 重构右子树，inoreder的stop为当前节点的值，找到则右子树重构完毕
                root.right = rebuild(root.val)
                # 注意右子树重构完毕，意味着inorder中已经不存在左子树的节点了
                # 那么现在该干什么？当然是pop掉当前的节点了
                inorder.pop()
                # 重构左子树，左子树的stop值为本函数的stop相同，初始为None
                root.left = rebuild(stop)
                return root
        # 哨兵节点
        inorder = [None] + inorder
        return rebuild(None)

s = com.leetcode.dp.q91.Solution()
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
tree = s.buildTree(inorder, postorder)
result = []
level_trav(tree, result)