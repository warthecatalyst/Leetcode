package interview.Baidu.Pro2;

import java.util.*;

public class Main {
    static Scanner scanner;
    static int N;
    static StringBuilder ans;
    static List<Integer> list;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        N = scanner.nextInt();
        ans = new StringBuilder();
        list = new ArrayList<>();
        int num = 1;
        while(getN(num)<=N){
            list.add(getN(num));
            num++;
        }
        //System.out.println(list);
        dfs(N,'d');
        System.out.println(ans);
    }

    public static void dfs(int curL,char c){
        if(curL==0){
            return;
        }
        int idx = list.size()-1;
        while(idx>=0&&list.get(idx)>curL){
            idx--;
        }
        //System.out.println(idx);
        for(int i = 0;i<=idx;i++){
            ans.append(c);
        }
        char nextC;
        if(c=='d'){
            nextC = 'e';
        }else if(c=='e'){
            nextC = 'r';
        }else{
            nextC = 'd';
        }
        dfs(curL-list.get(idx),nextC);
    }

    public static int getN(int num){
        return (1+num)*num/2;
    }
}
