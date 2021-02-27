## Math

### 位运算技巧

#### 136. Single Number

```
/*
* 136. Single Number
* 查找单独的数
* https://leetcode.com/problems/single-number/
* */
```

```java



/*
抑或技巧，不同等于相加，相同等于相减
Runtime: 1 ms, faster than 96.29% of Java online submissions for Single Number.
Memory Usage: 47.5 MB, less than 11.27% of Java online submissions for Single Number.
* */
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }
}
```

#### 191. Number of 1 Bits

```
/*
* 191. Number of 1 Bits
* 1的个数，汉明重量
* https://leetcode.com/problems/number-of-1-bits/
*
* */
```

```java
/*
位运算技巧，n&(n-1)必定能消除最后的1，包括符号位
Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.
Memory Usage: 35.7 MB, less than 83.47% of Java online submissions for Number of 1 Bits.
* */
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            ++res;
        }
        return res;
    }
}
```

#### 231. Power of Two

```
/*
* 231. Power of Two
* 2的次方数
* https://leetcode.com/problems/power-of-two/
*
*
* */
```

```java
/*
负数不是2的次方，只有一个1
Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
        Memory Usage: 38.2 MB, less than 8.77% of Java online submissions for Power of Two.
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n-1)) == 0;
    }
}
```

### 阶乘

#### 172. Factorial Trailing Zeroes

```
/*
* 172. Factorial Trailing Zeroes
* 阶乘尾0的个数
* https://leetcode.com/problems/factorial-trailing-zeroes/
* */
```

得到一个0其实取决于因子2和5.下面是暴力解法。
事实上，2的因子总是多于5的因子，所以有多少对2和5取决于5的数量。

时间复杂度：

对于任意n，需要计算k次，5^k = n，因而k = log n，所以整体时间复杂度为O(N log N)

```java
/*

Runtime: 35 ms, faster than 5.10% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 36.1 MB, less than 38.90% of Java online submissions for Factorial Trailing Zeroes.
* */

class Solution {
    public int trailingZeroes(int n) {
        int two, five, cur;
        two = five = 0;
        for (int i = 1; i <= n; i++) {
            cur = i;
            while (cur % 5 == 0) {
                cur /= 5;
                ++five;
            }
            while (cur % 2 == 0) {
                cur /= 2;
                ++two;
            }
        }
        return Math.min(two, five);
    }
}
```

```java
/*
以125为例

5，10，15，20... 5的倍数都能提供 1个5， 总共有125/5个

25,50,75,100,125... 25的倍数还能额外提供1个人5，总共125/25个

125，... 125的倍数还能额外提供一个5，总共125/125个

相加即可

算法复杂度分析：

取决于while循环的次数，k次，5^k = n，则k = log n，所以算法复杂度为O(log n)
Runtime: 0 ms, faster than 100.00% of Java online submissions for Factorial Trailing Zeroes.
Memory Usage: 35.7 MB, less than 80.01% of Java online submissions for Factorial Trailing Zeroes.
* */

class SolutionV2 {
    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }
}
```

#### 793. Preimage Size of Factorial Zeroes Function

```
/*
* 793. Preimage Size of Factorial Zeroes Function
* 阶乘尾0进阶
* https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
*
* */
```

```java
/*
* 793. Preimage Size of Factorial Zeroes Function
* 阶乘尾0进阶
* https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
*
* */
/*
超时
* */
class Solution {
    public int preimageSizeFZF(int K) {
        int res = 0, t;
        for (int i = 0; ; i++) {
            if ((t = trailingZeroes(i)) == K) ++res;
            else if (t>K) break;
        }
        return res;
    }

    public int trailingZeroes(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().preimageSizeFZF(80502705));
    }
}

/*
二分搜索，因为K和f(x)是单调不减函数，所以可以应用二分搜索
Runtime: 0 ms, faster than 100.00% of Java online submissions for Preimage Size of Factorial Zeroes Function.
        Memory Usage: 35.8 MB, less than 46.34% of Java online submissions for Preimage Size of Factorial Zeroes Function.
*/

class SolutionV2 {
    public int preimageSizeFZF(int K) {
        int l, r, mid;
        l = 0; r = 5 * K + 1;
        while (l < r) {
            mid = (l + r) >> 1;
            if (f(mid) < K) l = mid + 1;
            else r = mid;
        }
        return f(l) > K ? 0 : 5;
    }

    public int f(int n) {
        int d = 5, res = 0;
        while (d <= n) {
            res += n / d;
            d *= 5;
        }
        return res;
    }

}
```

### 素数

#### 204. Count Primes

```
/*
 * 204. Count Primes
 * 素数筛选法
 * https://leetcode.com/problems/count-primes/
 *
 * */
```

```java
/*
时间复杂度很难计算
* */
/*
素数筛选法效率版本
Runtime: 12 ms, faster than 88.99% of Java online submissions for Count Primes.
Memory Usage: 37.6 MB, less than 46.00% of Java online submissions for Count Primes.
*/

class SolutionV1 {
    public int countPrimes(int n) {
        int res = 0;
        boolean isPrime[] = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i])
                for (int j = i * i; j < n; j += i)isPrime[j] = false;
        }
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) ++res;
        }
        return res;
    }

    public static void main(String[] args) {
        new SolutionV1().countPrimes(10);
    }
}
```

### 幂

#### 372. Super Pow

```
/*
* 372. Super Pow
* 次方模
* https://leetcode.com/problems/super-pow/
* */
```

这道题首先要掌握两方面的知识
- 数学知识，乘积的模等于因子模乘积的模
    $$
    a \mod b = c \cdots d \\
    a * e \mod b = d * e\mod b
    $$
- 适用递归高效计算次方，利用指数运算缩小b的位数

算法复杂度分析，sPow调用宽度为1， 深度为O(N)，N为b的位数，sMod为O(1)，所以整体算法复杂度为O(N)

```java
/*

Runtime: 6 ms, faster than 68.60% of Java online submissions for Super Pow.
        Memory Usage: 39.7 MB, less than 20.93% of Java online submissions for Super Pow.
*/

class Solution {
    private int base;


    public int superPow(int a, int[] b) {
        base = 1337;
        return sPow(a, b, b.length);
    }

    private int sMod(int a, int b) {
        int r = 1;
        a = a % base;
        while (b-- > 0) {
            r = (r * a) % b;
        }
        return r;
    }

    private int sPow(int a, int[] b, int n) {
        if (n == 0) return 1;
        int r1 = sMod(a, b[n - 1]);
        int r2 = sMod(sPow(a, b, n - 1), 10);
        return r1 * r2 % base;
    }

}
```

### 随机数

#### 398. Random Pick Index

```
/*
* 398. Random Pick Index
* 获取目标值随机下标
* https://leetcode.com/problems/random-pick-index/
*
* */
```

直观的解法是利用map<int, list<int>>直接解决，空间O(N)时间O(1)

题目要求节省空间，那唯一想到的就是排序+二分搜索确定上下边界，排序O(N log N)，空间O(1)时间O(NlogN）

看起来似乎都能接受，实际的问题是，由于数组极大，O(N)的额外空间消耗是不被接受的，O(N logN)的排序算法
在数据量极大的情况下会非常慢，也无法接受。

能否有一种one pass的O(N)，空间O(1)的算法呢？水塘抽样算法（Reservoir Sampling）

这是一种数学方法，假设当前位置为i，RS算法的核心是让随后i到N的多次采样，对于i整体概率刚好为1/N

$$
\begin{aligned}
& \frac{1}{i} \times\left(1-\frac{1}{i+1}\right) \times\left(1-\frac{1}{i+2}\right) \times \ldots \times\left(1-\frac{1}{n}\right) \\
=& \frac{1}{i} \times \frac{i}{i+1} \times \frac{i+1}{i+2} \times \ldots \times \frac{n-1}{n} \\
=& \frac{1}{n}
\end{aligned}
$$

````java

int getRandom(ListNode head) {
        Random r = new Random();
        int i = 0, res = 0;
        ListNode p = head;
        // while 循环遍历链表
        while (p != null) {
        // 生成一个 [0, i) 之间的整数
        // 这个整数等于 0 的概率就是 1/i
        if (r.nextInt(++i) == 0) {
        res = p.val;
        }
        p = p.next;
        }
        return res;
        }
````

还有一种随机取k个元素的版本，遍历一次，随机取到k个元素

$$
\begin{aligned}
& \frac{k}{i} \times\left(1-\frac{k}{i+1} \times \frac{1}{k}\right) \times\left(1-\frac{k}{i+2} \times \frac{1}{k}\right) \times \ldots \times\left(1-\frac{k}{n} \times \frac{1}{k}\right) \\
=& \frac{k}{i} \times\left(1-\frac{1}{i+1}\right) \times\left(1-\frac{1}{i+2}\right) \times \ldots \times\left(1-\frac{1}{n}\right) \\
=& \frac{k}{i} \times \frac{i}{i+1} \times \frac{i+1}{i+2} \times \ldots \times \frac{n-1}{n} \\
=& \frac{k}{n}
\end{aligned}
$$

实现比较清晰，每次都有k / (i+1)的概率修改前k个元素中的某一个

````java
int[] getRandom(ListNode head, int k) {
        Random r = new Random();
        int[] res = new int[k];
        ListNode p = head;

        // 前 k 个元素先默认选上
        for (int j = 0; j < k && p != null; j++) {
        res[j] = p.val;
        p = p.next;
        }

        int i = k;
        // while 循环遍历链表
        while (p != null) {
        // 生成一个 [0, i) 之间的整数
        int j = r.nextInt(++i);
        // 这个整数小于 k 的概率就是 k/i
        if (j < k) {
        res[j] = p.val;
        }
        p = p.next;
        }
        return res;
        }
````

```java
/*

Runtime: 47 ms, faster than 87.11% of Java online submissions for Random Pick Index.
Memory Usage: 47 MB, less than 96.75% of Java online submissions for Random Pick Index.
* */

import java.util.Random;

class Solution {
    private int[] nums;
    private Random random;
    private int j, res, dup;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();

    }
    
    public int pick(int target) {
        dup = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i]!=target) continue;
            j = random.nextInt(++dup);
            if (j == 0) {
                res = i;
            }
        }
        return res;
    }

}
```

#### 382. Linked List Random Node

```
/*
 * 382. Linked List Random Node
 * 链表获取随机数
 * https://leetcode.com/problems/linked-list-random-node/
 *
 * */
```

见398
如果是为了getRandom为O(1)，则可以花费O(N)的空间，建立array保存所有值，用于随机。
如果为了不使用额外的空间，则必须遍历得知N的值，然后以O(N)进行链表随机节点获取

```java
/*

Runtime: 16 ms, faster than 21.99% of Java online submissions for Linked List Random Node.
Memory Usage: 40.8 MB, less than 78.85% of Java online submissions for Linked List Random Node.
* */


import java.util.Random;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    private Random random;
    private int i, j, res;
    private ListNode head,p;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public Solution(ListNode head) {
        random = new Random();
        this.head = head;
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        i = 0;
        p = head;
        while (p != null) {
            j = random.nextInt(++i);
            if (j == 0) res = p.val;
            p = p.next;
        }
        return res;
    }
}
```

### 重复

#### 448. Find All Numbers Disappeared in an Array

```
/*
* 448. Find All Numbers Disappeared in an Array
* 找到未出现的元素
* https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
*
* */
```

像这类题目，暴力解法有排序或hash（桶），但是时间复杂度和空间复杂度无法兼顾。

破局就在一个条件，n长的数组里给定的元素为1到n中的元素。于是可以通过索引来做一些文章，
求和，减法，抑或，负数等都是可以考虑的操作之一

```java
/*
Runtime: 4 ms
Memory Usage: 47.9 MB
* */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List res = new ArrayList<>();
        int t;
        for (int i = 0; i < nums.length; i++) {
            t = Math.abs(nums[i]);
            nums[t - 1] = -Math.abs(nums[t - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
```

#### 41. First Missing Positive

```
/*
* 41. First Missing Positive
* 寻找缺失的第一个整数
* https://leetcode.com/problems/first-missing-positive/
*
* */
```

```java
/*
题目要求空间复杂度为O(1)时间复杂度为O(N)
题目限制了nums.length的长度，以下暴力解法勉勉强强。
Runtime: 1 ms, faster than 43.11% of Java online submissions for First Missing Positive.
        Memory Usage: 37.2 MB, less than 19.04% of Java online submissions for First Missing Positive.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        Set set = new HashSet();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 1; i < 302; i++) {
            if (!set.contains(i)) return i;
        }
        return -1;
    }
}
/*
同样利用索引做文章，将nums[i]低于n的打上标记，
但是由于nums中本身有负数，为了避免混淆，需要将原本的负数处理到n之外。
最后再扫描一遍，找到第一个为正的位置。
Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.
        Memory Usage: 36.6 MB, less than 74.75% of Java online submissions for First Missing Positive.

*/



class SolutionV2 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length, j;
        for (int i = 0; i < n; i++)
            if (nums[i] <= 0) nums[i] = n + 1;
        for (int i = 0; i < n; i++) {
            int idx = nums[i] < 0 ? -nums[i] - 1 : nums[i] - 1;
            if (idx < n && nums[idx] > 0) nums[idx] = -nums[idx];
        }
        for (j = 1; j <= n; j++) {
            if (nums[j-1] > 0) return j;
        }
        return j;
    }
}
/*
同样对索引做文章，若nums[i]<n则交换到正确的位置上（nums[i]-1），然后扫描一次，找到不在位置上的元素
Runtime: 0 ms, faster than 100.00% of Java online submissions for First Missing Positive.
Memory Usage: 36.5 MB, less than 92.55% of Java online submissions for First Missing Positive.
* */
public class SolutionV3 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length, t, idx;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n ||nums[i] == nums[nums[i]-1]) continue;
            idx = nums[i]-1;
            nums[i] = nums[idx];
            nums[idx] = idx + 1;
            i--;
        }
        for (t = 1; t <= n; ++t) {
            if (nums[t-1]!=t) break;
        }
        return t;
    }
}
```

#### 442. Find All Duplicates in an Array

```
/*
* 442. Find All Duplicates in an Array
* 寻找所有重复的元素
* https://leetcode.com/problems/find-all-duplicates-in-an-array/
*
* */
```

```java
/*
同理q448
Runtime: 5 ms, faster than 89.65% of Java online submissions for Find All Duplicates in an Array.
Memory Usage: 48.6 MB, less than 22.49% of Java online submissions for Find All Duplicates in an Array
* */

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List res = new ArrayList();
        for (int idx : nums) {
            idx = idx < 0 ? -idx - 1 : idx - 1;
            if (nums[idx] < 0) res.add(idx + 1);
            else nums[idx] = -nums[idx];
        }
        return res;
    }
}
```

#### 645. Set Mismatch

```
/*
 * 645. Set Mismatch
 * 找到重复和缺失
 * https://leetcode.com/problems/set-mismatch/
 * */
```

```java
/*
像这类题目，暴力解法有排序或hash（桶），但是时间复杂度和空间复杂度无法兼顾。

破局就在一个条件，n长的数组里给定的元素为1到n中的元素。于是可以通过索引来做一些文章，
求和，减法，抑或，负数等都是可以考虑的操作之一
Runtime: 2 ms, faster than 86.18% of Java online submissions for Set Mismatch.
Memory Usage: 40.7 MB, less than 51.04% of Java online submissions for Set Mismatch.
* */
class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup=0, miss=0;
        for (int i : nums) {
            i = i < 0 ? -i - 1 : i - 1;
            if (nums[i] > 0) nums[i] = -nums[i];
            else dup = i + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) miss = i + 1;
        }
        return new int[]{dup, miss};
    }
}
/*
结合抑或特性，one pass
Runtime: 3 ms, faster than 57.59% of Java online submissions for Set Mismatch.
Memory Usage: 40.3 MB, less than 87.14% of Java online submissions for Set Mismatch.
* */


class SolutionV2 {
    public int[] findErrorNums(int[] nums) {
        int res[] = new int[2], idx;
        for (int i = 0; i < nums.length; i++) {
            idx = nums[i] < 0 ? -nums[i]-1 : nums[i]-1;
            if (nums[idx] < 0) {
                res[0] = idx + 1;
            } else {
                res[1] ^= idx;
                nums[idx] = -nums[idx];
            }
            res[1] ^= i;
        }
        res[1]+=1;
        return res;
    }
}
```

### Game

#### 292. Nim Game

```
/*
292. Nim Game
石头游戏
https://leetcode.com/problems/nim-game/
* */
```

```java
/*
292. Nim Game
石头游戏
https://leetcode.com/problems/nim-game/
* */
/*
关键，胜负是有规律的
111011101110
Runtime: 0 ms, faster than 100.00% of Java online submissions for Nim Game.
Memory Usage: 35.5 MB, less than 82.92% of Java online submissions for Nim Game.
* */
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

#### 319. Bulb Switcher

```
/*
* 319. Bulb Switcher
* 关灯游戏
* https://leetcode.com/problems/bulb-switcher/
* */
```

第i轮，每隔i个toggle一盏灯。

从整体的角度来思考，最终每盏灯亮或不亮，取决于它被开关多少次。
对于i，一般情况它的因子都是成对出现，例如8，（1，8)(2,4)，这种情况下必然灭掉
除非i本身等于某个平方数，则因子为奇数，例如9,(1,9)(3)
所以，有多少个灯不灭，其实就等于多少个这样的平方数。

```java
/*

Runtime: 0 ms, faster than 100.00% of Java online submissions for Bulb Switcher.
Memory Usage: 35.9 MB, less than 30.51% of Java online submissions for Bulb Switcher.
* */
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
```