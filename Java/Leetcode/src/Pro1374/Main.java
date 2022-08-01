package Pro1374;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String generateTheString(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        if(n%2==0){
            stringBuilder.append("a".repeat(Math.max(0, n - 1)));
            stringBuilder.append('b');
        }else{
            stringBuilder.append("a".repeat(n));
        }
        return stringBuilder.toString();
    }
}
