package Pro2315;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countAsterisks(String s) {
        int cnt = 0;
        int cnt_l = 0;
        for(char c:s.toCharArray()){
            if(c=='|'){
                cnt_l++;
            }else if(c == '*'){
                if(cnt_l%2==0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
