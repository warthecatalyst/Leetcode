package pro814;

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

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return DFS(root);
    }

    private TreeNode DFS(TreeNode cur){
        if(cur==null){
            return null;
        }
        cur.left = DFS(cur.left);
        cur.right = DFS(cur.right);
        if(cur.left==null&&cur.right==null&&cur.val==0){
            return null;
        }
        return cur;
    }
}
