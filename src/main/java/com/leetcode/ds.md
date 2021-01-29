## 数据结构

要求实现数据结构的题目。

核心就是，各种集合轮子的应用，需要考虑他们的API，各种操作的复杂度。以及，实现轮子的能力。

## 问题

#### **并查集**

用于连通性判断，路径压缩，核心代码如下：

```java
        private int find(int x) {
            while (parent[x] != x) {
                // 进行路径压缩
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
```

- 130，**被包围的x**，找到被包围的x，核心的连通性判断
- 990，**等式是否成立**，核心仍是连通性判断

#### **`LinkedHashMap`**

`linkedList`的插入删除非常快$O(1)$，但是本身无法做到快速定位$O(N)$，因而结合`hashMap`，为其提供value到node节点引用的快速定位功能。

- 146，**实现LRU Cache**，常见的的一种队列策略的实现，主要就是`linkedHashMap`的应用
- 460，**实现LFU Cache**，按频率，比较难想到很全面的实现，比如这个 题，应该使用`freq -> linkedHashMap`才能很好保证LFU的各项效率。

#### **单调栈和队列**

**单调栈**

最经典的案例是：一排人站成一列找身高更高的——此时身高低的会被直接挡住，只会看到更高的。

实现的基本思想是，比入栈元素小的栈顶元素都会被出栈。这使得栈保持严格的单调性（单调递减）。

从另一个角度，若不然，则会出现5 1 2 4，事实上被4挡住的1 2 对于后面随后的计算是没有用处的，在找到更高的人的场景下，你只能看到4.

算法复杂度的思考很经典，考虑所有元素有且仅有出栈入栈一次。 

````java
int[] ans = new int[T.length];
Stack<Integer> stack = new Stack();
for (int i = T.length - 1; i >= 0; --i) {
    while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
    ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
    stack.push(i);
}
````

- 496，下一个更大的数，两数组，单调栈
- 503，循环数组，下一个更大的数，两次遍历
- 739，下一个更温暖的日子需要多久，变体

**单调队列**

它们保证单调的基本手段是——元素进入前，首先移除队列末尾不符合单调条元素。

详细可以参考题目 239的注解。

单调队列的模板。

````java
class ManoQueue{
    private LinkedList<Integer> list;

    public ManoQueue() {
        list = new LinkedList<>();
    }

    public void push(int cur) {
        while (!list.isEmpty() && cur > list.getLast()) list.removeLast();
        list.addLast(cur);
    }

    public int max() {
        return list.getFirst();
    }

    public void pop(int cur) {
        if (list.getFirst() == cur)  list.removeFirst();
    }
}
````

滑动窗口的模板。

````java
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                q.push(nums[i]);
            } else {
                q.push(nums[i]);
                res[idx++] = q.max();
                q.pop(nums[i - k + 1]);
            }
        }
````

- 239，返回滑动窗口的最大值，在O(1)的时间完成该操作，单调队列解决。

#### **堆**

-  [最大堆实现](datastructure\head\最大堆实现.md) 

- 295，流的中位数，大小堆

**多个有序列表的合并**

````java
所有列表头节点进堆
while (!pq.isEmpty() && r.size() < 10) {
     tw = pq.poll();
     r.add(tw.tid);
     if (tw.next!=null)pq.offer(tw.next);
}
````

- 355，推特模拟，多个有序列表的合并，面向对象编程

#### **队列和栈的相互实现**

- 225，使用队列实现栈，单队列，pop的时候将n-1个元素重新放到队尾。
- 232，栈实现队列，双栈，使用双栈两次改变顺序还原队列顺序

#### 二分搜索

二分搜索需要记住模板，边界条件要求比较严格。

````java
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (!isPossible(weights, mid, D)) lo = mid + 1;
            else hi = mid;
        }
````

- 875，吃完所有香蕉，每天最少吃多少。确定上下界，二分搜索
- 1011，运完所有包裹，最少容量。确定上下界，二分搜索。



