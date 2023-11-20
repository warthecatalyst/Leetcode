package Pro117;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class Solution {
    private List<List<Node>> nodesOfLevels;
    public Node connect(Node root) {
        nodesOfLevels = new ArrayList<>();
        DFS(root, 0);
        for(List<Node> nodeList : nodesOfLevels) {
            for(int i = 0; i < nodeList.size(); i++) {
                if (i < nodeList.size() - 1) {
                    nodeList.get(i).next = nodeList.get(i+1);
                }
            }
        }
        return root;
    }

    public void DFS(Node cur, int level) {
        if(cur == null) {
            return;
        }
        while(level >= nodesOfLevels.size()) {
            nodesOfLevels.add(new ArrayList<>());
        }
        nodesOfLevels.get(level).add(cur);
        DFS(cur.left, level+1);
        DFS(cur.right, level+1);
    }
}
