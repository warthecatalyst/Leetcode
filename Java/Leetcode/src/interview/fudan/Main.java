package interview.fudan;

import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){this.val = val;}
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TreeNode[] treeNodes;
    static int[] fathers;
    static int rootNode;
    public static void main(String[] args) {
        int N = scanner.nextInt();
        treeNodes = new TreeNode[N];
        fathers = new int[N];
        for(int i = 0;i<N;i++){
            int val = scanner.nextInt();
            if(i==0){   //根节点
                rootNode = val-1;
                treeNodes[rootNode] = new TreeNode(val);
                fathers[rootNode] = 0;
            }else{      //找到应该插入的位置
                int curNode = val-1;
                treeNodes[curNode] = new TreeNode(val);
                DFS(val,treeNodes[rootNode]);
            }
        }
        for(int father:fathers){
            System.out.print(father+" ");
        }
    }

    public static void DFS(int val,TreeNode node){
        if(val<node.val){
            if(node.left==null){
                node.left = treeNodes[val-1];
                fathers[val-1] = node.val;
            }else{
                DFS(val,node.left);
            }
        }else{
            if(node.right==null){
                node.right = treeNodes[val-1];
                fathers[val-1] = node.val;
            }else{
                DFS(val,node.right);
            }
        }
    }
}
