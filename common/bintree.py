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
    def __str__(self):
        return f'{self.val}'
""" 
重构
"""

def lev_rebuild(nodes):
    N = len(nodes)
    if N <= 0: return None
    root = Node(val = nodes[0])
    q = [root]
    i = 1
    while True:
        if i >= N: break
        t = []
        for cur in q:
            if nodes[i]!='null':
                cur.left = Node(val=nodes[i])
                t.append(cur.left)
            i += 1
            if i == N: break
            if nodes[i]!='null':
                cur.right = Node(val=nodes[i])
                t.append(cur.right)
            i += 1
        q = t
    return root


""" 
遍历
"""
def trav(root, mode = 'lev', disp = True):
    result = []
    if mode == 'lev':
        f = lev_trav
    elif mode == 'pre':
        f = pre_trav
    f(root, result)
    if disp:
        print(result)
    return result

def pre_trav(root, result):
    if root:
        result.append(root.val)
        pre_trav(root.left, result)
        pre_trav(root.right, result)
    result.append(None)
    
# 层次遍历
def lev_trav(root, result):
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
    root = [1,2,3,4,'null',2,4,'null','null',4]
    root = lev_rebuild(root)
    result = []
    level_trav(root, result)