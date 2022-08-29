package Pro793;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int preimageSizeFZF(int k) {
        long l = 0,r = (long)1e10;
        while(l<r){
            long mid = (l+r)>>1;
            long sum = 0;
            long cur = 5;
            while(cur<=mid){
                sum += mid/cur;
                cur*=5;
            }
            if(sum==k){
                return 5;
            }else if(sum<k){
                l = mid;
            }else{
                r = mid+1;
            }
        }
        return 0;
    }
}
