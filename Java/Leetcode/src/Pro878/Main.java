package Pro878;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    static final int MOD = (int)1e9+7;

    public int nthMagicalNumber(int n, int a, int b) {
        long l = Math.min(a, b);
        long r = (long) n * Math.min(a, b);
        int c = lcm(a, b);
        while (l <= r) {
            long mid = (l + r) / 2;
            long cnt = mid / a + mid / b - mid / c;
            if (cnt >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ((r + 1) % MOD);
    }

    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    public int lcm(int a,int b){
        return a*b/gcd(a,b);
    }
}