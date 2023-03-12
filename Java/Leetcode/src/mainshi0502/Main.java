package mainshi0502;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String printBin(double num) {
        StringBuilder stringBuilder = new StringBuilder("0.");
        while(stringBuilder.length()<32&&num!=0){
            num *= 2;
            int digit = (int) num;
            stringBuilder.append(digit);
            num-=digit;
        }
        if(num!=0){
            return "ERROR";
        }
        return stringBuilder.toString();
    }
}
