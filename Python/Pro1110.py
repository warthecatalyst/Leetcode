# Definition for a binary tree node.
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def delNodes(self, root: Optional[TreeNode], to_delete: List[int]) -> List[TreeNode]:
        to_delete_set = set(to_delete)
        roots = []

        def dfs(node: Optional[TreeNode], is_root: bool) -> Optional[TreeNode]:
            if node is None:
                return None
            delete = node.val in to_delete_set
            node.left = dfs(node.left, delete)
            node.right = dfs(node.right, delete)
            if delete:
                return None
            else:
                if is_root:
                    roots.append(node)
                return node

        dfs(root, True)
        return roots
