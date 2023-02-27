package interview.Ali_Xiaoxi;

public class Solution{
    public static void main(String[] args){
        int num = 111;
        System.out.println(isHuiwen2(num));
    }

    private static boolean isHuiwen2(int num){
        int reverse = 0;
        int temp = num;
        while(temp>0){
            reverse = reverse*10+temp%10;
            temp /= 10;
        }
        return reverse == num;
    }
}
