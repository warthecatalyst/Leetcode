package Pro2180;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countEven(int num) {
        int cnt = 0;
        for(int i = 1;i<=num;i++){
            if(isEven(i)){
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isEven(int num){
        int res = 0;
        while(num>0){
            res += num%10;
            num/=10;
        }
        return res%2==0;
    }
}