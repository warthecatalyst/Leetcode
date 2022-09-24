package Pro1652;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if(k==0){
            for(int i = 0;i<n;i++){
                ans[i] = k;
            }
        }else if(k>0){
            for(int i = 0;i<n;i++){
                for(int j = 1;j<=k;j++){
                    int index = (i+j)%n;
                    ans[i] += code[index];
                }
            }
        }else{  //k < 0
            k = -k;
            for(int i = 0;i<n;i++){
                for(int j = 1;j<=k;j++){
                    int index = (i-j+n)%n;
                    ans[i] += code[index];
                }
            }
        }
        return ans;
    }
}
