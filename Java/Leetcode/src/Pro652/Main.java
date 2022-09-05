package Pro652;

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

class Solution {
    Map<String,TreeNode> treeNodeMap = new HashMap<>();
    Set<TreeNode> set = new HashSet<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        DFS(root);
        return new ArrayList<>(set);
    }

    public String DFS(TreeNode cur){
        if(cur==null){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cur.val);
        stringBuilder.append('(');
        stringBuilder.append(DFS(cur.left));
        stringBuilder.append(")(");
        stringBuilder.append(DFS(cur.right));

        String s = stringBuilder.toString();
        if(treeNodeMap.containsKey(s)){
            set.add(treeNodeMap.get(s));
        }else{
            treeNodeMap.put(s,cur);
        }
        return s;
    }
}
