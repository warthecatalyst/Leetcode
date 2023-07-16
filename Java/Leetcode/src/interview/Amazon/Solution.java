package interview.Amazon;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] shoeSizes = new int[]{10,3,1,21,17,18,1000};
        int[] queries = new int[]{6,17,2,9,50,10000};

        Arrays.sort(shoeSizes);
        for(int query:queries){
            int ans = getBestShoeSize(shoeSizes,query);
            System.out.println(ans);
        }
    }

    public static int getBestShoeSize(int[] shoeSizes,int query){
        int left = 0,right = shoeSizes.length-1;
        if(shoeSizes[shoeSizes.length-1]<=query){
            return -1;
        }
        while(left<right){
            int mid = (left+right)/2;
            if(shoeSizes[mid]>query){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return shoeSizes[right];
    }
}
