package forInter.NiukeCompetition2.Pro4;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static double s,x,m;
    public static void main(String[] args) {
        String str = scanner.next();
        String[] strs = str.split(",");
        s = Double.parseDouble(strs[0]);
        x = Double.parseDouble(strs[1]);
        m = Double.parseDouble(strs[2]);
        double giveAway = s*x/12+s/m;
        //System.out.println(giveAway);
        double minD = 0;
        double minRes = Integer.MAX_VALUE;
        for(double d1 = 0;d1<=0.3;d1+=0.0001){
            //利率为d1
            double total = s;   //本金为s
            for(int i = 0;i<m;i++){
                //第i期
                double rev = total*d1/12;
                total -= (giveAway-rev);
            }
            if(Math.abs(total)<minRes){
                minRes = Math.abs(total);
                minD = d1;
            }
        }
        System.out.printf("%.4f",minD);
    }

    static boolean logicalEquals(double l1,double l2){
        return Math.abs(l1-l2)<=0.0001;
    }
}
