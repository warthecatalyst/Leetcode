package Pro6294;


import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    List<Integer>[] graph;
    int n;
    public long maxOutput(int n, int[][] edges, int[] price) {
        this.n = n;
        graph = new List[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge:edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        long maxOut = 0;

        for(int i = 0;i<n;i++){
            long maxPath = getMaxPath(i,price);
            //System.out.println("current root is: "+i+" ,maxVal = "+maxPath);
            maxOut = Math.max(maxOut,maxPath-price[i]);
        }
        return maxOut;
    }

    long getMaxPath(int root,int[] price){
        boolean[] vis = new boolean[n];
        return dfs(root,price,vis);
    }

    long dfs(int cur,int[] price,boolean[] visited){
        visited[cur] = true;
        long maxVal = 0;
        for(int neigh:graph[cur]){
            if(visited[neigh]){
                continue;
            }
            maxVal = Math.max(maxVal,dfs(neigh,price,visited));
        }
        return maxVal+price[cur];
    }
}
