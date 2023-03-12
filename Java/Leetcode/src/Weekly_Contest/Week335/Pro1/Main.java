package Weekly_Contest.Week335.Pro1;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int passThePillow(int n, int time) {
        int k = time%(n-1);
        int l = time/(n-1);
        if(l%2==0){
            return k+1;
        }else{
            return n-k;
        }
    }
}
