package Pro1619;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public double trimMean(int[] arr) {
        int len = arr.length;
        int per5 = (int) (len*0.05);
        double sum = 0;
        Arrays.sort(arr);
        for(int i = per5;i<len-per5;i++){
            sum+=arr[i];
        }
        return sum/(len-2*per5);
    }
}
