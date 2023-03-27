package interview.tencent.Pro2;

import java.util.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N;
    static int ans;
    static Set<Character>[] stringSets;
    public static void main(String[] args) {
        N = scanner.nextInt();
        stringSets = new Set[N];
        for(int i = 0;i<N;i++){
            String temp = scanner.next();
            stringSets[i] = new HashSet<>();
            for(char c:temp.toCharArray()){
                stringSets[i].add(c);
            }
        }
        dfs(0, new StringBuilder(), new HashSet<Character>());
        System.out.println(ans);
    }

    public static void dfs(int cur,StringBuilder stringBuilder,Set<Character> usedChars){
        if(cur==N){
            ans++;
            return;
        }
        for(char c:stringSets[cur]){
            if(!usedChars.contains(c)){
                stringBuilder.append(c);
                usedChars.add(c);
                dfs(cur+1,stringBuilder,usedChars);
                usedChars.remove(c);
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
        }
    }
}
