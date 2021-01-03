# 652. Find Duplicate Subtrees
# Medium
# 查找树内的重复子树，返回所有重复子树列表，列表去重
# 1685

# 225

# Add to List

# Share
# Given the root of a binary tree, return all duplicate subtrees.

# For each kind of duplicate subtrees, you only need to return the root node of any one of them.

# Two trees are duplicate if they have the same structure with the same node values.

 

# Example 1:


# Input: root = [1,2,3,4,null,2,4,null,null,4]
# Output: [[2,4],[4]]



import sys
sys.path.append("..")
from common.bintree import Node as TreeNode
from common.bintree import trav,lev_rebuild
import collections
""" 
version 1
-
思路：

后序遍历描述树的样子（满树后序唯一），map记录其他树的样子
-
时间复杂度：

递归树深度与树的平衡性有关，最差情况下深度为N，对于每一层的所有分支，算法复杂度为O(N)——来自hash函数计算，它和子树的节点数量有关，左右子树结合和为N。所以，这个算法是明显的O(N^2)算法。理想情况下，我们可以期待它O（logN N）。
-
空间复杂度：

N个节点进入dict，O(N)
-
重复hash：

效率低的原因之一是hash重复计算，例如对于root，即使子树的hash已经计算完成，仍然需要计算整颗数的所有节点。
-
运行时间：

Runtime: 52 ms, faster than 81.61% of Python online submissions for Find Duplicate Subtrees.
Memory Usage: 23.2 MB, less than 62.54% of Python online submissions for Find Duplicate Subtrees.

"""
 class Solution(object):
    def findDuplicateSubtrees(self, root):
        sel, vis = {}, set()
        def trav(root):
            if not root: return '#'
            left = trav(root.left)
            right = trav(root.right)
            cur = left + ',' + right + ',' + str(root.val)
            if cur in vis:
                sel[cur] = root
            else:
                vis.add(cur)
            return cur
        trav(root)
        return list(sel.values())


root = [1,2,3,4,'null',2,4,'null','null',4]
root = lev_rebuild(root)
trav(root)
s = Solution()
for x in s.findDuplicateSubtrees(root):
    print(x, end=' ')

'''
version 2
-
改进version 1提到的重复hash问题。这个问题有点意思，比较容易绕晕，其实核心是，子树的hash值能否缓存，直接作为该节点的hash值的计算——这样就不用每次都计算子树hash了。
对比下面两个版本，tuple实现和frozenset实现，核心的区别是，frozenset会缓存hash值，而它的hash值计算是取出所有元素的hash值进行计算，而tuple的hash和元素内容相关，没有缓存。可以参考frozenset的hash实现。
-
空间复杂度：
关于该版本的空间复杂度，由于dict需要保存每一个子树的所有节点，对于每一层所有节点子树节点的和为N，因而又是和树的深度有关，最差O(N^2)，否则O(N logN)
-
时间复杂度：
看起来每次都能在O(1)的时间内计算完成hash值，但隐藏的隐患来自hash函数本身，它只是摊还分析意义上的O(1)，在极端情况下，不断出现hash冲突，则还是O(N)
-
ref: 
version2 源码，StefanPochmann的大佬，分析非常精彩。 https://leetcode.com/problems/find-duplicate-subtrees/discuss/106016/O(n)-time-and-space-lots-of-analysis
frozenset hash函数：https://stackoverflow.com/questions/20832279/python-frozenset-hashing-algorithm-implementation
'''
class Solution(object):
    def findDuplicateSubtrees(self, root):
        def tuplify(root):
            if root:
                tuple = root.val, tuplify(root.left), tuplify(root.right)
                trees[tuple].append(root)
                return tuple
        trees = collections.defaultdict(list)
        tuplify(root)
        return [roots[0] for roots in trees.values() if roots[1:]]


class Solution(object):
    def findDuplicateSubtrees(self, root):
        def convert(root):
            if root:
                result = frozenset([(root.val, convert(root.left), convert(root.right))])
                trees[result].append(root)
                return result
        trees = collections.defaultdict(list)
        convert(root)
        return [roots[0] for roots in trees.values() if roots[1:]]


'''
version 3
-
思路：
解决上述问题，其实有个常用的技巧，编号。以下的实现极为精彩。
ref:
https://leetcode.com/problems/find-duplicate-subtrees/discuss/106016/O(n)-time-and-space-lots-of-analysis
'''
    class Solution(object):
    def findDuplicateSubtrees(self, root, heights=[]):
        def getid(root):
            if root:
                id = treeid[root.val, getid(root.left), getid(root.right)]
                trees[id].append(root)
                return id
        trees = collections.defaultdict(list)
        # 不声明默认值
        treeid = collections.defaultdict()
        # 将它绑定它自己的size函数，每次添加元素的时候，默认等于一个新的编号
        treeid.default_factory = treeid.__len__
        getid(root)
        return [roots[0] for roots in trees.values() if roots[1:]]


root = [1,2,3,4,'null',2,4,'null','null',4]
root = lev_rebuild(root)
s = Solution()
s.findDuplicateSubtrees(root)



# =====================================
from timeit import timeit
# 性能测试
# 构造一棵极端右偏树
def tree(height):
    if height:
        root = TreeNode(val=0)
        root.left = tree(height-1)
        return root

func = Solution().findDuplicateSubtrees
for n in range(5, 12):
    # 节点数指数增长，每次翻倍
    root = tree(2**n)
    print(timeit(lambda: func(root), number = 1000))

# version 1
# 每次时间x4，符合O(N^2)预期
# 0.0224624
# 0.0439131
# 0.10434309999999998
# 0.2960343
# 0.8601814
# 2.4694431999999997
# 12.7502278

# version 2 frozenset版本
# 0.022957699999778924
# 0.04559870000002775
# 0.08996769999998833
# 0.18135209999991275
# 0.36198399999989306
# 0.7442135999999664
# 1.4799183999998604
