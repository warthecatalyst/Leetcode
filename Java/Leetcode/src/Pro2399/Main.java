package Pro2399;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.checkDistances("abaccb",new int[]{
                1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        });

    }
}

class Solution {
    public boolean checkDistances(String s, int[] distance) {
        List<Integer>[] charPositions = new List[26];
        for(int i = 0;i<26;i++){
            charPositions[i] = new ArrayList<>();
        }
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            charPositions[c - 'a'].add(i);
        }

        for(int i = 0;i<26;i++){
            List<Integer> positions = charPositions[i];
            /*System.out.println((char)('a'+i)+":");
            System.out.println(positions);*/
            if(positions.isEmpty()){
                continue;
            }
            int dis = distance[i];
            for(int j = 0;j<positions.size()-1;j++){
                int curPos = positions.get(j),nextPos = positions.get(j+1);
                if(nextPos-curPos!=dis+1){
                    return false;
                }
            }
        }
        return true;
    }
}
