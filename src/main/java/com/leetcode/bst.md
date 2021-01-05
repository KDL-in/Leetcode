# 框架

````java
void BST(TreeNode root, int target) {
    if (root.val == target)
        // 找到目标，做点什么
    if (root.val < target) 
        BST(root.right, target);
    if (root.val > target)
        BST(root.left, target);
}
````

就是依靠当前值大小判断进入下一步递归。

# 问题

- 230，寻找二分搜索树第k大的元素，递归实现，stack模拟递归
- 450，最基础的BST删除，找到后继，替换删除
- 538，1038，BST遍历求累加和，累加BST中大于等于当前节点的所有val的和，所以遍历需要反向。二叉树的复杂度和树高H相关，这是一个很好的思考角度
- 700，BST搜索，简单题
- 701，BST插入节点
- 98，BST合法性检查，root传递约束给子节点。

