### 滑动窗口

固定窗口

```java
Window w = new Window(s1);
while (r < s2.length()) {
    w.add(s2.charAt(r++));
    if (r > w.size) {
        w.remove(s2.charAt(l++));
    }
    // 调试
    // System.out.println(l+ " " + r);
    // w.disp();
    if (w.check()) return true;
}
```

变长窗口

````java
while (right < s.length()) {
    w.add(s.charAt(right++));
    // 收缩左边
    if (w.check()) {
        while (w.check()) {
            w.remove(s.charAt(left++));
            // System.out.println(left + " " + right);
        }
        if (rr - rl > right - left + 1) {
            rr = right;
            rl = left - 1;
        }
    }

}
````

窗口本身

````java
class Window {

    int[] map;

    int size, idx;

    public Window() {
        map = new int[130];
    }

    public void add(char c) {
        if (++map[c] == 2) idx = c;
        ++size;
    }

    public void remove(char c) {
        --map[c];
        --size;
    }

    public boolean check() {
        if (map[idx] == 2) return true;
        return false;
    }

    public void disp() {
        for (int i = 'A'; i <= 'z'; i++) {
            System.out.print(map[i] + " ");
        }
        System.out.println();
    }
}
````



3，不重复的最长字串。固定窗口问题。

76，经典问题，最小覆盖字串，变长窗口问题。

438，578，寻找全排列字串，固定窗口问题。

### 单调栈

维护一个单调栈来找到一个覆盖数组。从而实现找到“下一个更大的元素”的目的。

````java
for (int i = nums2.length - 1; i >= 0; i--) {
    while (!s.isEmpty() && s.peek() <= nums2[i]) s.pop();
    res[i] = s.isEmpty() ? -1 : s.peek();
    s.push(nums2[i]);
}
````

*496，找到下一个更大的元素，如上思想，加上map解决

503，环形数组，遍历两次即可解决

739，求间隔，stack存入下标，利用stack中大于当前数的下标-当前下标来找到间隔。

### 堆

*295 寻找流的中位数。你不必维护全局有序，开销太大。使用大顶堆和小顶堆，让二者size一致即可。

355模拟twitter，要实现的API包括用户之间的follower，指定用户id发帖，getNews获得一个用户所有follower的帖子，按时间排序。每个用户followers可以用map来记录，发帖可以挂在用户中成为有序列表。然后getNews则是多个列表的合并。

### LinkedHashMap

*146，LRU。最近使用的需要放在前面，需要删除最久为使用的，其实linkedList可以解决这两个问题，快速的插入和删除。主要问题在于更新的对象链表已经存在，那么如何去快速查找。答案是，加上hashmap

*460，LFU。关键是需要对频率进行实时排序，快速插入删除，更新频率，同频率使用LRU。这其实比较麻烦。想到排序的话，会想用红黑树，可惜大多数操作只能是log n，而且无法保证同频率LRU。所以解决方案是，map<freq,  linkedHashMap>

###  并查集

````java
    private void union(int a, int b, int h, int w) {
        int ra = find(a);
        int rb = find(b);
        if (哪个集合更小) parent[rb] = ra;
        else parent[ra] = rb;
    }

    private int find(int cur) {
        while (parent[cur] != cur) {
            parent[cur] = parent[parent[cur]];
            cur = parent[cur];
        }
        return parent[cur];
    }
````

130 找到被包围的0

990 检查公式传递性

### 链表

**翻转链表**

翻转链表有两种思路：

迭代实现：

关键维护`pre`和`cur`两个指针，边遍历边翻转，`cur.next = pre`，细节需要稍微控制，此外`dumy`节点的添加有利于减小编程难度。

*递归实现：

````java
    private ListNode reverseLinkList(ListNode head) {

        if (head.next == null) return head;
        // 逆转剩余链表，并且返回最后一个
        ListNode last = reverseLinkList(head.next);
        // 当前head成为最后一个
        head.next.next = head;
        head.next = null;
        // 返回最后一个节点，即新的头部节点。
        return last;
    }
````

> 25 按组翻转链表, 92 翻转 n到m的链表

- 92 移动到n+1节点，翻转接下来的m个节点
- 25 按组翻转，递归实现，翻转当前head之后的n个节点，递归翻转n+1之后的所有节点，返回新的头节点
- 234 回文链表，要求空间O(1)，因此借助链表翻转，取中间节点，翻转一边，然后对比之

### 二叉树

**改变结构**

*114 经典问题，平摊二叉树，平摊左，平摊右，连接

````java
def flatten(root):
    if root:
        # 摊平 left tree
        flatten(root.left)
        # 摊平 right tree
        flatten(root.right)
        # root -> left left的最后->right
        left, right = root.left, root.right
        root.left,root.right  = None, left
        while root.right:
            root = root.right
        root.right = right
````

*116 横向连接二叉树，考虑root一层难以连接完整，需要递归两层

````python
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
````

105 106 654 重构二叉树，根据不同顺序的遍历数组，找到左右子树的边界，重构左，重构右。

652 寻找重复子树，二叉树序列化，`left + ',' + right ',' + val`，满树二叉树（这里指子节点为#非空，满二叉树是二叉树中所有非叶子结点的度都是2，且叶子结点都在同一层次上）后序遍历唯一。该题需要知道左子树字符串是否重复，右子树字符串是否重复，因此用后序较为合适。

297 二叉树序列化反序列化，#标记树的结束的null节点，前序容易实现反序列化

341 无限嵌套列表迭代器实现，一种方法是视为多叉树，遍历直接拿出数据，另一种是懒加载方式，一旦当前的节点是list，则将它加入linkedlist的首部，继续循环，直到拆解出int

*236 最小公共祖先

````java
    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 找到其中一个节点，不需要往下递归
        if (root == p || root == q) return root;
        // 在左子树中查找
        TreeNode left = findLCA(root.left, p, q);
        // 在右子树中查找
        TreeNode right = findLCA(root.right, p, q);
        // 左右子树中找到目标p，q，说明root是p，q的LCA
        if (left != null && right != null) return root;
        // 都找不到，返回null
        else if (left == null && right == null) return null;
        // 找到一个，返回一个
        else return left == null ? right : left;
    }
````

222 满二叉树节点计算，递归实现，如果最左的子节点的高度和最右的子节点高度一致，则直接公式计算

### 二叉搜索树

230 bst的第k小的数。中序。

*538 bst累加，当前`curSum = 大于该节点值的右子树的求和 +root.val`，需要传给左子树当前值。	 

*98 bst验证bst的合法性，左子树应该位于(min, root.val)之间，右子树应该位于(root.val, max)之间

````java
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
````

*450 701 700 bst的删查插，删除需要分子树节点数量讨论。

# 其他

## 滑动窗口

