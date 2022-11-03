package Pro1620;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        for(int[] tower:towers){
            int x = tower[0], y = tower[1];
            xMax = Math.max(xMax,x);
            yMax = Math.max(yMax,y);
        }
        double maxPower = 0;
        int x_ans = 0, y_ans = 0;
        for(int x = 0;x<=xMax;x++){
            for(int y = 0;y<=yMax;y++){
                double pw = 0;
                for(int[] tower:towers){
                    int x_t = tower[0], y_t = tower[1];
                    double dis = getDistance(x,y,x_t,y_t);
                    if(dis>radius){
                        continue;
                    }
                    pw += (int)(tower[2]/(1+dis));
                }
                if(pw>maxPower){
                    x_ans = x;
                    y_ans = y;
                    maxPower = pw;
                }
            }
        }
        return new int[]{x_ans,y_ans};
    }

    private double getDistance(int x1,int y1,int x2,int y2){
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
}