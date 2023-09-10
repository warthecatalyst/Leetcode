package DataStructure.Pro1921;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arrivalTime = new int[n];
        for(int i = 0;i < n;i++) {
            arrivalTime[i] = (dist[i]-1)/speed[i]+1;
        }
        Arrays.sort(arrivalTime);
        for(int i = 0;i<n;i++) {
            if(arrivalTime[i] <= i) {
                return i;
            }
        }
        return n;
    }
}
