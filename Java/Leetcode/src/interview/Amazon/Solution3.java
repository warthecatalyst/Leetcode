package interview.Amazon;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(rangeFreqQuery.query(1,2,4));
        System.out.println(rangeFreqQuery.query(0,11,33));
    }
}

class RangeFreqQuery {
    int[] array;
    Map<Integer,List<Integer>> numCount;
    public RangeFreqQuery(int[] arr) {
        array = arr;
        int n = arr.length;
        numCount = new HashMap<>();
        for(int i = 0;i<n;i++){
            int key = arr[i];
            List<Integer> list = numCount.getOrDefault(key,new ArrayList<>());
            list.add(i);
            numCount.put(key,list);
        }
        System.out.println(numCount);
    }

    public int query(int left, int right, int value) {
        List<Integer> list = numCount.getOrDefault(value,new ArrayList<>());
        //System.out.println(list);
        if(list.isEmpty()||left>list.get(list.size()-1)||right<list.get(0)){
            return 0;
        }
        //二分找到第一个比left大的
        int li = 0,ri = list.size()-1;
        while(li<ri){
            int mid = (li+ri)/2;
            if(list.get(mid)>left){
                ri = mid;
            }else{
                li = mid+1;
            }
        }
        int leftAns = ri;
        //System.out.println("leftAns = "+leftAns);
        //二分找到第一个比right小的
        li = 0;
        ri = list.size()-1;
        while(li<ri){
            int mid = (li+ri)/2;
            if(list.get(mid)<right){
                li = mid+1;
            }else{
                ri = mid-1;
            }
        }
        int rightAns = ri;
        //System.out.println("rightAns = "+rightAns);
        return rightAns-leftAns+1;
    }
}
