package forInter.NiukeCompetition.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        int ans = (n-6)/10;
        if(ans%2==1){
            System.out.println("kou");
        }else{
            System.out.println("yukari");
        }
    }
}
