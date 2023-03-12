package Weekly_Contest.Week335.Pro2;

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
    List<Long> levelSum;
    public long kthLargestLevelSum(TreeNode root, int k) {
        levelSum = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root,0));
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.level>=levelSum.size()){
                levelSum.add(0L);
            }
            levelSum.set(cur.level,levelSum.get(cur.level)+cur.node.val);
            if(cur.node.left!=null){
                queue.add(new Node(cur.node.left,cur.level+1));
            }
            if(cur.node.right!=null){
                queue.add(new Node(cur.node.right,cur.level+1));
            }
        }

        Collections.sort(levelSum);
        if(k>levelSum.size()){
            return -1;
        }
        return levelSum.get(levelSum.size()-k);
    }

    static class Node{
        TreeNode node;
        int level;
        public Node(){}
        public Node(TreeNode node,int level){
            this.node = node;
            this.level = level;
        }
    }
}
