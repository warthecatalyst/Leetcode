package interview.PDD.Pro3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static int N;
    static int twoStart,threeStart;
    static int[] fee;
    static int[] maxPerson;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        N = scanner.nextInt();
        String[] orders = new String[N];
        for(int i = 0;i<N;i++){
            orders[i] = scanner.next();
        }
        maxPerson = new int[3];
        fee = new int[3];
        for(int i = 0;i<3;i++){
            maxPerson[i] = scanner.nextInt();
            fee[i] = scanner.nextInt();
        }
        Arrays.sort(orders, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });
        twoStart = -1;
        threeStart = -1;
        for(int i =0;i<N;i++){
            if(twoStart==-1&&orders[i].length()==2){
                twoStart = i;
            }if(threeStart==-1&&orders[i].length()==3){
                threeStart = i;
            }
        }
        int[] curLeft = maxPerson.clone();
        boolean res = process1(orders,twoStart,curLeft);
        res &= process2(orders,twoStart,threeStart,curLeft);
        res &= process3(orders,threeStart,curLeft);
        if(res){
            System.out.println("YES");
//            System.out.println(curLeft[0]);
//            System.out.println(curLeft[1]);
//            System.out.println(curLeft[2]);
            int ans = 0;
            ans += (maxPerson[0]-curLeft[0])*fee[0];
            ans += (maxPerson[1]-curLeft[1])*fee[1];
            ans += (maxPerson[2]-curLeft[2])*fee[2];
            System.out.println(ans);
        }else{
            System.out.println("NO");
            int ans = 0;
            ans += (maxPerson[0]-curLeft[0]);
            ans += (maxPerson[1]-curLeft[1]);
            ans += (maxPerson[2]-curLeft[2]);
            System.out.println(ans);
        }
    }

    static boolean process1(String[] orders,int twoStart,int[] curLeft){
        if(twoStart==-1){
            twoStart = orders.length;
        }
        for(int i = 0;i<twoStart;i++){
            String order = orders[i];
            int idx = order.charAt(0)-'A';
            if(curLeft[idx]>0){
                curLeft[idx]--;
            }else{
                return false;
            }
        }
        return true;
    }

    static boolean process2(String[] orders,int twoStart,int threeStart,int[] curLeft){
        if(threeStart==-1){
            threeStart = orders.length;
        }
        return DFS(twoStart,orders,threeStart,curLeft);
    }

    static boolean DFS(int idx,String[] orders,int end,int[] curLeft){
        if(idx==end){
            return true;
        }
        //System.out.println("idx = "+idx+", curLeft = "+Arrays.toString(curLeft));
        String order = orders[idx];
        int idx1 = order.charAt(0)-'A';
        int idx2 = order.charAt(1)-'A';
        if(curLeft[idx1]>0&&curLeft[idx2]>0){
            int minIdx,maxIdx;
            if(fee[idx1]<=fee[idx2]){
                minIdx = idx1;
                maxIdx = idx2;
            }else{
                minIdx = idx2;
                maxIdx = idx1;
            }
            curLeft[minIdx]--;
            boolean res = DFS(idx+1,orders,end,curLeft);
            if(res){
                return true;
            }
            curLeft[minIdx]++;
            curLeft[maxIdx]--;
            res = DFS(idx+1,orders,end,curLeft);
            return res;
        }
        else if(curLeft[idx1]>0){
            curLeft[idx1]--;
            boolean res = DFS(idx+1,orders,end,curLeft);
            if(res){
                return true;
            }
            curLeft[idx1]++;
            return false;
        }else if(curLeft[idx2]>0){
            curLeft[idx2]--;
            boolean res = DFS(idx+1,orders,end,curLeft);
            if(res){
                return true;
            }
            curLeft[idx2]++;
            return false;
        }else{
            return false;
        }
    }

    static boolean process3(String[] orders,int threeStart,int[] curLeft){
        if(threeStart==-1){
            return true;
        }
        for(int i = threeStart;i<orders.length;i++){
            int ans = chooseBest(curLeft);
            if(ans == -1){
                return false;
            }
            curLeft[ans]--;
        }
        return true;
    }

    //三个找最佳
    public static int chooseBest(int[] curLeft){
        //假设存在一个没有，就去剩下两个找，包含了所有情况
        if(curLeft[0]==0){
            return chooseBest("BC",curLeft);
        }else if(curLeft[1]==0){
            return chooseBest("AC",curLeft);
        }else if(curLeft[2]==0){
            return chooseBest("AB",curLeft);
        }

        //三个都有
        if(fee[0]<=fee[1]&&fee[0]<=fee[2]){
            return 0;
        }else if(fee[1]<=fee[2]){
            return 1;
        }else {
            return 2;
        }
    }

    //两个找最佳
    public static int chooseBest(String order,int[] curLeft){
        int idx1 = order.charAt(0)-'A';
        int idx2 = order.charAt(1)-'A';
        if(curLeft[idx1]==0&&curLeft[idx2]==0){
            //都没有额外的人了
            return -1;
        }else if(curLeft[idx1]>0&&curLeft[idx2]>0){
            //两者都有，选钱少的
            if(fee[idx1]<fee[idx2]){
                return idx1;
            }else{
                return idx2;
            }
        }else{
            if(curLeft[idx1]>0){
                return idx1;
            }else{
                return idx2;
            }
        }
    }
}

