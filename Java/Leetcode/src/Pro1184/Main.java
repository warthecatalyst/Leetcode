package Pro1184;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if(start>=destination){
            int tmp = destination;
            destination = start;
            start = tmp;
        }
        //destination>=start
        int sum1 = 0,sum2 = 0;
        for(int i = start;i<destination;i++){
            sum1 += distance[i];
        }
        for(int i = (start-1+distance.length)%distance.length;i!=destination;i = (i-1+distance.length)%distance.length){
            sum2+=distance[i];
        }
        System.out.println(sum1+" "+sum2);
        return Math.min(sum1,sum2);
    }
}