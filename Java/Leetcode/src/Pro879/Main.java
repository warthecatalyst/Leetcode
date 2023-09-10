package Pro879;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maxDistToClosest(int[] seats) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<seats.length;i++) {
            if(seats[i] == 1) {
                list.add(i);
            }
        }
        int minDistance = Math.max(list.get(0), seats.length-1-list.get(list.size()-1));
        for(int i = 0;i < list.size()-1;i++) {
            int left = list.get(i), right = list.get(i+1);
            if((right-left)/2 > minDistance) {
                minDistance = (right-left)/2;
            }
        }
        return minDistance;
    }
}
