""" 
==========================================
Task:

654. Maximum Binary Tree
二叉树构造，最大值划分

==========================================
Notes:
1. 关注算法复杂度，不要关注trick！


==========================================
Examples:

Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]

===========================================
"""
import sys
sys.path.append("..")
from common.bintree import Node as TreeNode
from common.bintree import level_trav
# version 1
# Runtime: 492 ms, faster than 5.70% of Python online submissions for Maximum Binary Tree.
# Memory Usage: 14 MB, less than 84.03% of Python online submissions for Maximum Binary Tree.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        def rebuild(nums):
            if len(nums) > 0:
                # find max val and idx
                max_val = -1
                max_idx = -1
                for i,x in enumerate(nums):
                    max_val,max_idx = (x, i) if x > max_val else (max_val, max_idx)
                print(nums, max_val)
                return TreeNode(val = max_val, left = rebuild(nums[:max_idx]), right = rebuild(nums[max_idx+1:]))
        return rebuild(nums)

# version 2
# 空间复杂度优化，上下标传递
# Runtime: 216 ms, faster than 31.18% of Python online submissions for Maximum Binary Tree.
# Memory Usage: 14.2 MB, less than 60.84% of Python online submissions for Maximum Binary Tree.
class TreeNode(object):
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        def rebuild(low, high):
            if (high - low) > 0:
                # find max val and idx
                max_val = -1
                for i in range(low, high):
                    if nums[i] > max_val: max_val,max_idx = (nums[i], i)
                return TreeNode(val = max_val, left = rebuild(low, max_idx), right = rebuild(max_idx+1, high))
        return rebuild(0, len(nums))




# test
nums = [3,2,1,6,0,5]
s = Solution()
max_tree = s.constructMaximumBinaryTree(nums)
result = []
level_trav(max_tree, result)

# version 2
# 动态规划，但出乎意料，表现极差
# 分析了一下，动态规划是实打实的n^2，递归算法最好的是n log n
# Runtime: 5188 ms, faster than 5.62% of Python online submissions for Maximum Binary Tree.
# Memory Usage: 73.5 MB, less than 12.73% of Python online submissions for Maximum Binary Tree.

class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        N = len(nums)
        dp_idx = [[-1]*N for _ in range(N)]
        # 对角线
        for i in range(N):
            dp_idx[i][i] = i
        for i in range(N):
            for c in range(i+1, N):
                r = c-i-1
                dp_idx[r][c] = dp_idx[r][c-1] if nums[dp_idx[r][c-1]] > nums[dp_idx[r+1][c]] else dp_idx[r+1][c]

        def rebuild(low, high):
            if (high - low) > 0:
                # find max val and idx
                return TreeNode(val = nums[dp_idx[low][high-1]], left = rebuild(low, dp_idx[low][high-1]), right = rebuild(dp_idx[low][high-1]+1, high))

        return rebuild(0, len(nums))
# test
nums = [3,2,1,6,0,5]
s = Solution()
max_tree = s.constructMaximumBinaryTree(nums)
result = []
level_trav(max_tree, result)

# version 2
# 动态规划，备忘录，最差O(n^2)，但使用备忘录进行剪枝，利用O（n）的空间换时间
# Runtime: 204 ms, faster than 46.44% of Python online submissions for Maximum Binary Tree.
# Memory Usage: 14.4 MB, less than 12.73% of Python online submissions for Maximum Binary Tree.
class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        dp_idx = list(range(len(nums)))
    
        def rebuild(low, high, flag):
            if (high - low) > 0:
                # find max val and idx
                if flag:
                    max_idx= dp_idx[high-1]
                    max_val = nums[max_idx]
                else:
                    max_val = -1
                    for i in range(low, high):
                        if nums[i] > max_val: 
                            print(dp_idx[i])
                            max_val,max_idx = (nums[i], i)
                        dp_idx[i]= max_idx
                return TreeNode(val = max_val, left = rebuild(low, max_idx, True), right = rebuild(max_idx+1, high, False))

        return rebuild(0, len(nums), False)

nums = [3,2,1,6,0,5]
s = Solution()
max_tree = s.constructMaximumBinaryTree(nums)
result = []
level_trav(max_tree, result)

# version 1.1
# 这个看起来很傻的代码到是很快，不是很懂，大概是，c++实现比较快？
# 除了算法原因，不要去纠结快慢，python太奇怪了，没预估时间
# 188 ms	14.2 MB
class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        def rebuild(nums):
            if len(nums) > 0:
                # find max val and idx
                max_idx = nums.index(max(nums))
                max_val = nums[max_idx]
                return TreeNode(val = max_val, left = rebuild(nums[:max_idx]), right = rebuild(nums[max_idx+1:]))
        return rebuild(nums)


# version 3
# Runtime: 168 ms, faster than 97.40% of Python online submissions for Maximum Binary Tree.
# Memory Usage: 14 MB, less than 84.39% of Python online submissions for Maximum Binary Tree.
class Solution(object):
    def constructMaximumBinaryTree(self, nums):
        stack = []
        for num in nums:
            # print(num, ': ', end=' ')
            # for x in stack:
            #     print(x.val, end=' ')
            # print()
            cur = TreeNode(val=num)
            # 找到比它小的做它左子节点
            while stack and stack[-1].val < cur.val:
                cur.left = stack.pop()
            # 找到比它大的最小节点x，它作为x的右子节点
            if stack:
                stack[-1].right = cur
            # cur称为右子树最末的节点，入栈
            stack.append(cur)
        return stack[0]

nums = [3,2,1,6,0,5]
s = Solution()
max_tree = s.constructMaximumBinaryTree(nums)
result = []
level_trav(max_tree, result)