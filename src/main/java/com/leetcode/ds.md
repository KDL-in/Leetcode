## 数据结构

要求实现数据结构的题目。

核心就是，各种集合轮子的应用，需要考虑他们的API，各种操作的复杂度。以及，实现轮子的能力。

### 问题

**并查集**

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

**`LinkedHashMap`**

`linkedList`的插入删除非常快$O(1)$，但是本身无法做到快速定位$O(N)$，因而结合`hashMap`，为其提供value到node节点引用的快速定位功能。

- 146，**实现LRU Cache**，常见的的一种队列策略的实现，主要就是`linkedHashMap`的应用
- 460，**实现LFU Cache**，按频率，比较难想到很全面的实现，比如这个 题，应该使用`freq -> linkedHashMap`才能很好保证LFU的各项效率。

**其他**

- 295，流的中位数，大小堆