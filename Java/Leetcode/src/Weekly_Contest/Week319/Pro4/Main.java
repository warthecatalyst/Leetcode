package Weekly_Contest.Week319.Pro4;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if(k==1) return n;
        boolean[][] rota = new boolean[n][n];
        List<int[]> endPoint = new ArrayList<>();
        for(int i = 0; i < n; i++){
            rota[i][i] = true;
        }
        for(int i = 0;i < n-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                rota[i][i+1] = true;
                if(k==2){
                    endPoint.add(new int[]{i+1,1});
                }
            }
        }
        for(int t = 2; t <=k; t++){
            for(int i = 0; i < n-t; i++){
                if(s.charAt(i) == s.charAt(i+1) && rota[i+1][i+t-1]){
                    rota[i][i+t] = true;
                    if(t==k || t==k-1){
                        endPoint.add(new int[]{i+t,t});
                    }
                }
            }
        }
        int res = 0,lastone = -1;
        endPoint.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for(int[] i : endPoint){
            if(lastone == -1 || i[0]-lastone > i[1]){
                res++;
                lastone = i[0];
            }
        }
        return res;
    }
}


