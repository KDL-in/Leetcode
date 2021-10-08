""" 
==========================================
Task:

46. Permutations
全排列
https://leetcode.com/problems/permutations/

==========================================
Notes:

1. python中可变对象是引用传递(类似java)，不可变对象为值传递
2. path[:]切片会默认复制
3. 内部函数，减少参数传递会快一点

==========================================
Examples:

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:
Input: nums = [1]
Output: [[1]] 

===========================================
"""

# backtrack 1
from typing import List

class com.leetcode.dp.q91.Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        return solve(nums)

def solve(sel):

    def backtrack(sel, flag, path):
        if len(path) == N:
            paths.append(path[:])
            return 

        for i in range(N):
            if flag[i] == True: continue
            # select
            path.append(sel[i])
            flag[i] = True
            # backtrack
            backtrack(sel, flag, path)
            # remove
            path.pop()
            flag[i] = False

    N = len(sel)
    paths,flag, path = [], [False] * N, []
    backtrack(sel, flag, path)
    # print(paths)
    return paths

class com.leetcode.dp.q91.Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        return solve(nums)

def solve(sel):

    def backtrack(flag, path):
        if len(path) == N:
            paths.append(path[:])
            return 

        for i in range(N):
            if flag[i] == True: continue
            # select
            path.append(sel[i])
            flag[i] = True
            # backtrack
            backtrack(flag, path)
            # remove
            path.pop()
            flag[i] = False

    N = len(sel)
    paths,flag, path = [], [False] * N, []
    backtrack(flag, path)
    # print(paths)
    return paths


nums = [1,2,3]
s = com.leetcode.dp.q91.Solution()
s.permute(nums)

