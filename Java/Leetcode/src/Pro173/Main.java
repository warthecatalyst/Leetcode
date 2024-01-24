package Pro173;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BSTIterator {
    private List<Integer> arr;
    private int idx;
    private void inOrder(TreeNode cur) {
        if (cur == null) {
            return;
        }
        inOrder(cur.left);
        arr.add(cur.val);
        inOrder(cur.right);
    }
    public BSTIterator(TreeNode root) {
        arr = new ArrayList<>();
        idx = 0;
        inOrder(root);
    }

    public int next() {
        return arr.get(idx++);
    }

    public boolean hasNext() {
        return idx < arr.size();
    }
}
