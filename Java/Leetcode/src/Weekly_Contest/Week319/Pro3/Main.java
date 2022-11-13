package Weekly_Contest.Week319.Pro3;

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
    public int minimumOperations(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(root,0));
        while(!queue.isEmpty()){
            Node cur = queue.peek();
            queue.poll();
            if(cur.node==null){
                continue;
            }
            while(lists.size() < cur.depth+1){
                lists.add(new ArrayList<>());
            }
            lists.get(cur.depth).add(cur.node.val);
            queue.add(new Node(cur.node.left, cur.depth+1));
            queue.add(new Node(cur.node.right,cur.depth+1));
        }
        System.out.println("ddd1");
        int res = 0;
        for(List<Integer> list:lists){
            res+=getUnorderedNum(list);
        }
        return res;
    }

    public int getUnorderedNum(List<Integer> list){
        List<Integer> orderedList = new ArrayList<>(list);
        Collections.sort(orderedList);
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<orderedList.size();i++){
            map.put(orderedList.get(i),i);
        }
        int[] order = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            order[i] = map.get(list.get(i));
        }
        return getOrder(order);
    }

    public int getOrder(int[] orders){
        int[] ordered = orders.clone();
        Arrays.sort(ordered);
        int res = 0;
        while(!Arrays.equals(orders, ordered)){
            for(int i =0;i<ordered.length;i++){
                int num = ordered[i];
                if(ordered[num]!=num){
                    int tmp = ordered[num];
                    ordered[num] = num;
                    ordered[i] = tmp;
                    res++;
                    System.out.println(Arrays.toString(orders));
                    break;
                }
            }
        }
        return res;
    }

    static class Node{
        TreeNode node;
        int depth;

        Node(){};

        Node(TreeNode node,int depth){
            this.node = node;
            this.depth = depth;
        }
    }
}