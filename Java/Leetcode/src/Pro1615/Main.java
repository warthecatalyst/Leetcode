package Pro1615;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        List<Integer>[] graph = new List[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] road:roads){
            int from = road[0],to = road[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                int tt = graph[i].size();
                tt += graph[j].size();
                if(graph[i].contains(j)){
                    tt--;
                }
                ans = Math.max(ans,tt);
            }
        }
        return ans;
    }
}
