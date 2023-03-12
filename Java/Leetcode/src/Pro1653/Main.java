package Pro1653;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int minimumDeletions(String s) {
        int leftb = 0, righta = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='a'){
                righta++;
            }
        }
        int res = righta;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='a'){
                righta--;
            }else{
                leftb++;
            }
            res = Math.min(res,leftb+righta);
        }
        return res;
    }
}
