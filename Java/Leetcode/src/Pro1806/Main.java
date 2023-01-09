package Pro1806;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        for(int i = 0;i<n;i++){
            perm[i] = i;
        }
        int cnt = 0;
        while(true){
            int[] arr = new int[n];
            cnt++;
            for(int i = 0;i<n;i++){
                if(i%2==0){
                    arr[i] = perm[i/2];
                }else{
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                }
            }
            perm = arr;
            if(isIni(perm)){
                return cnt;
            }
        }
    }

    private boolean isIni(int[] perm){
        for(int i = 0;i<perm.length;i++){
            if(perm[i]!=i){
                return false;
            }
        }
        return true;
    }
}
