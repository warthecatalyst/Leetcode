package interview.Mihayo.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt();
        while(N-->0){
            String src = scanner.next();
            String dest = scanner.next();
            if(src.length()>dest.length()){
                String temp = dest;
                dest = src;
                src = temp;
            }
            //System.out.println("src = "+src+" ,dest = "+dest);
            //双指针判断
            int n = src.length(),m = dest.length();
            if((m-n)%3!=0){
                System.out.println("No");
                continue;
            }
            int i = 0,j = 0;
            int[] adds = new int[3];
            boolean canChange = true;
            while(i<n&&j<m){
                if(src.charAt(i)==dest.charAt(j)){
                    i++;
                }else{
                    if (changeAdds(dest, j, adds)) break;
                }
                if(!checkCanChange(adds)){
                    canChange = false;
                    break;
                }
                j++;
            }
            if(!canChange){
                System.out.println("No");
                continue;
            }
            while(i==n&&j<m){
                if (changeAdds(dest, j, adds)) break;
                if(!checkCanChange(adds)){
                    canChange = false;
                }
                j++;
            }
            if(canChange){
                canChange = i == n && j == m && adds[0] == adds[1] && adds[0] == adds[2];
            }
            if(canChange){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }

    static boolean changeAdds(String dest, int j, int[] adds) {
        if(dest.charAt(j)=='m'){
            adds[0]++;
        }else if(dest.charAt(j)=='h'){
            adds[1]++;
        }else if(dest.charAt(j)=='y'){
            adds[2]++;
        }else{
            return true;
        }
        return false;
    }

    static boolean checkCanChange(int[] adds){
        //System.out.println("adds = "+Arrays.toString(adds));
        return adds[0]>=adds[1]&&adds[0]>=adds[2]&&adds[1]>=adds[2];
    }
}