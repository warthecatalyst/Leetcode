package Pro871;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        });
        int ans = 0,prev = 0;
        int n = stations.length;
        for(int i = 0;i<=n;i++){
            int cur = i==n?target:stations[i][0];
            startFuel -= cur-prev;
            while(startFuel<0&&!priorityQueue.isEmpty()){
                startFuel+=priorityQueue.poll();
                ans++;
            }
            if(startFuel<0){
                return -1;
            }
            if(i<n){
                priorityQueue.offer(stations[i][1]);
                prev = cur;
            }
        }
        return ans;
    }
}
