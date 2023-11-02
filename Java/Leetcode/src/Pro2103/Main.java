package Pro2103;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countPoints(String rings) {
        boolean[][] ringCount = new boolean[10][3];
        for(int i = 0; i < rings.length();i+=2) {
            char ring = rings.charAt(i);
            int num = rings.charAt(i+1) - '0';
            if (ring == 'R') {
                ringCount[num][0] = true;
            } else if (ring == 'G') {
                ringCount[num][1] = true;
            } else {
                ringCount[num][2] = true;
            }
        }
        int cnt = 0;
        for(int i = 0; i < 10; i++) {
            boolean flag = true;
            for (int j = 0; j < 3; j++) {
                if(!ringCount[i][j]) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                cnt++;
            }
        }
        return cnt;
    }
}
