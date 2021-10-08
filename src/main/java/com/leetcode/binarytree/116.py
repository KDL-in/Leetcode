""" 
==========================================
Task:

116. Populating Next Right Pointers in Each Node
连接树的右邻节点
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

==========================================
Notes:



==========================================
Examples:



===========================================
"""
# Runtime: 264 ms, faster than 5.12% of Python online submissions for Populating Next Right Pointers in Each Node.
# Memory Usage: 16.6 MB, less than 18.15% of Python online submissions for Populating Next Right Pointers in Each Node.
class com.leetcode.dp.q91.Solution(object):
    def connect(self, root):
        def connectTwo(left, right):
            if left == right: return 
            # 连接当前左右字节
            left.next = right
            # 递归连接子树节点
            connectTwo(left.left, left.right)
            connectTwo(left.right, right.left)
            connectTwo(right.left, right.right)
        if root: connectTwo(root.left, root.right)

class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next

def rebuild(i):
    if i < len(nodes):
        return Node(val=nodes[i], left=rebuild((i<<1)+1),right=rebuild((i<<1)+2))


def pre_trav(root, result):
    if root:
        result.append(root.val)
        pre_trav(root.left, result)
        pre_trav(root.right, result)
    result.append(None)
nodes = [1,2,3,4,5,6,7]
root = rebuild(0)
result = []
pre_trav(root, result)


s = com.leetcode.dp.q91.Solution()
s.connect(root)