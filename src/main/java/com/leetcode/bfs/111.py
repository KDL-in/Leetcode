""" 
==========================================
Task:

752. Open the Lock
开密码锁
https://leetcode.com/problems/open-the-lock/

==========================================
Notes:



==========================================
Examples:

Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:

Input: deadends = ["0000"], target = "8888"
Output: -1

===========================================
"""


from collections import deque
# version 1
# 普通的BFS
# Runtime: 808 ms, faster than 28.47% of Python online submissions for Open the Lock.
# Memory Usage: 15 MB, less than 12.94% of Python online submissions for Open the Lock.
class Solution(object):
    def openLock(self, deadends, target):
        def rot_at(s, i, move):
            return s[:i] + str((int(s[i]) + move + N) % N) + s[i + 1:]

        vis = set(deadends)
        LEN, N, step = len(target), 10, 0
        q = deque(['0000'])

        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                # check
                if cur in vis: continue
                if cur == target: return step
                vis.add(cur)
                # put all node into q
                for i in range(LEN):
                    t = rot_at(cur, i, +1)
                    q.append(t)
                    t = rot_at(cur, i, -1)
                    q.append(t)
            step += 1
        return -1

# version 1.5
# 访问时机需要修正一下，尽管看起来繁琐增加，但实际上该版本有两个好处
# - vis检查时机，在for循环试探中进行试探。这样做避免了大量不必要的节点进入队列，所以运行速度提高了；另一方面，ver 1检查vis的位置也容易出问题
# Runtime: 688 ms, faster than 38.82% of Python online submissions for Open the Lock.
# Memory Usage: 14.4 MB, less than 64.24% of Python online submissions for Open the Lock.
class Solution(object):
    def openLock(self, deadends, target):
        def rot_at(s, i, move):
            return s[:i] + str((int(s[i]) + move + N) % N) + s[i + 1:]

        deadends,vis = set(deadends), set()
        LEN, N, step = len(target), 10, 0
        q = deque(['0000'])

        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                # check
                if cur in deadends: continue
                if cur == target: return step
                vis.add(cur)
                # put all node into q
                for i in range(LEN):
                    t = rot_at(cur, i, +1)
                    if t not in vis:
                        q.append(t)
                    t = rot_at(cur, i, -1)
                    if t not in vis:
                        q.append(t)
            step += 1
        return -1

# version 2
# 双向BFS
# - 双向BFS是一种优化的trick，因为单个节点BFS越久，节点越多，时间复杂度越高
# - 交替探测是一个非常优雅的实现
# Runtime: 140 ms, faster than 96.71% of Python online submissions for Open the Lock.
# Memory Usage: 14.1 MB, less than 95.76% of Python online submissions for Open the Lock.
class Solution(object):
    def openLock(self, deadends, target):
        def rot_at(s, i, move):
            return s[:i] + str((int(s[i]) + move + N) % N) + s[i + 1:]

        deadends, vis, q1, q2 = set(deadends), set(), set(), set()
        LEN, N, step = len(target), 10, 0
        q1.add('0000')
        q2.add(target)
        while q1:
            temp = set() # 创建临时set，保存下层的所有结果
            for cur in q1:
                # check
                if cur in deadends: continue
                if cur in q2: return step # 如果双向BFS相遇
                vis.add(cur)
                # put all node into q
                for i in range(LEN):
                    t = rot_at(cur, i, +1)
                    if t not in vis:
                        temp.add(t)

                    t = rot_at(cur, i, -1)
                    if t not in vis:
                        temp.add(t)
            step += 1
            # 交替探测
            q1 = q2
            q2 = temp
        return -1

# version 2.1
# 取消deadends，只是用vis
# Runtime: 136 ms, faster than 96.94% of Python online submissions for Open the Lock.
# Memory Usage: 14.2 MB, less than 95.76% of Python online submissions for Open the Lock.
class Solution(object):
    def openLock(self, deadends, target):
        def rot_at(s, i, move):
            return s[:i] + str((int(s[i]) + move + N) % N) + s[i + 1:]
        vis, q1, q2 = set(deadends), set(), set()
        LEN, N, step = len(target), 10, 0
        q1.add('0000')
        q2.add(target)
        # 单独处理初始无解情况
        if '0000' in vis: return -1
        while q1:
            temp = set() # 创建临时set，保存下层的所有结果
            for cur in q1:
                # check
                if cur in q2: return step # 如果双向BFS相遇
                vis.add(cur)
                # put all node into q
                for i in range(LEN):
                    t = rot_at(cur, i, +1)
                    if t not in vis:
                        temp.add(t)

                    t = rot_at(cur, i, -1)
                    if t not in vis:
                        temp.add(t)
            step += 1
            # 交替探测
            q1 = q2
            q2 = temp
        return -1

# version 2.5
# 双向BFS优化，谁少探测谁
# Runtime: 136 ms, faster than 96.94% of Python online submissions for Open the Lock.
# Memory Usage: 14.1 MB, less than 95.76% of Python online submissions for Open the Lock.
class Solution(object):
    def openLock(self, deadends, target):
        def rot_at(s, i, move):
            return s[:i] + str((int(s[i]) + move + N) % N) + s[i + 1:]
        vis, q1, q2 = set(deadends), set(), set()
        LEN, N, step = len(target), 10, 0
        q1.add('0000')
        q2.add(target)
        # 单独处理初始无解情况
        if '0000' in vis: return -1
        while q1:
            temp = set() # 创建临时set，保存下层的所有结果
            for cur in q1:
                # check
                if cur in q2: return step # 如果双向BFS相遇
                vis.add(cur)
                # put all node into q
                for i in range(LEN):
                    t = rot_at(cur, i, +1)
                    if t not in vis:
                        temp.add(t)

                    t = rot_at(cur, i, -1)
                    if t not in vis:
                        temp.add(t)
            step += 1
            # 交替探测，谁节点少优先检测
            if len(q2) < len(temp):
                q1 = q2
                q2 = temp
            else:
                q1 = temp
        return -1


deadends = ["0201", "0101", "0102", "1212", "2002"]
# deadends = []

target = "0202"
s = Solution()
s.openLock(deadends, target)


