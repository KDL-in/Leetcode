from collections import deque
""" 
数据结构
"""
class Node(object):
    def __init__(self, val=0, left=None, right=None, next=None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
""" 
重构
"""
def rebuild(i):
    if i < len(nodes):
        return Node(val=nodes[i], left=rebuild((i<<1)+1),right=rebuild((i<<1)+2))

""" 
遍历
"""
def pre_trav(root, result):
    if root:
        result.append(root.val)
        pre_trav(root.left, result)
        pre_trav(root.right, result)
    result.append(None)
    
# 层次遍历
def level_trav(root, result):
    q = [root]
    while q:
        qt = []
        for cur in q:
            if cur:
                result.append(cur.val)
                qt.append(cur.left)
                qt.append(cur.right)
            else:
                result.append(None)
        q = qt
        
if __name__ == "__main__":
    nodes = [1,2,3,4,5,6,7]
    root = rebuild(0)
    result = []
    level_trav(root, result)