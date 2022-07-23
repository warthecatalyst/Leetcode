package pro1175;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    private static final int MOD = (int)1e9+7;
    public int numPrimeArrangements(int n) {
        int primeCnt = 0;
        for(int i = 1;i<=n;i++){
            if(isPrime(i)){
                primeCnt++;
            }
        }
        System.out.println(primeCnt);
        return (int)(factorial(primeCnt)*factorial(n-primeCnt)%MOD);
    }

    private boolean isPrime(int n){
        if(n==1){
            return false;
        }
        for(int i = 2;i*i<=n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    private long factorial(int n){
        long num = 1;
        for(int i = 2;i<=n;i++){
            num *= i;
            num %= MOD;
        }
        return num;
    }
}