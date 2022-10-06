package Pro1694;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String reformatNumber(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        for(char c:number.toCharArray()){
            if(Character.isDigit(c)){
                stringBuilder.append(c);
            }
        }
        String num = stringBuilder.toString();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<num.length();){
            if(num.length()-i>4){
                sb.append(num, i, i+3);
                sb.append('-');
                i+=3;
            }else if(num.length()-i==4){
                sb.append(num,i,i+2);
                sb.append('-');
                sb.append(num.substring(i+2));
                break;
            }else{
                sb.append(num.substring(i));
                break;
            }
        }
        return sb.toString();
    }
}
