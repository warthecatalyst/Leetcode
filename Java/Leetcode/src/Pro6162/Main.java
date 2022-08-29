package Pro6162;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int garbageCollection(String[] garbage, int[] travel) {
        int n = garbage.length;
        Map<Character,Integer>[] maps = new Map[n];
        int[] ends = new int[3];
        for(int i = 0;i<n;i++){
            maps[i] = new HashMap<>();
            String str = garbage[i];
            for(char c:str.toCharArray()){
                maps[i].put(c,maps[i].getOrDefault(c,0)+1);
            }
            if(maps[i].containsKey('M')){
                ends[0] = i;
            }
            if(maps[i].containsKey('P')){
                ends[1] = i;
            }
            if(maps[i].containsKey('G')){
                ends[2] = i;
            }
        }
        int sum = 0;
        //metal
        for(int i = 0;i<=ends[0];i++){
            sum+=maps[i].getOrDefault('M',0);
            if(i<ends[0]){
                sum+=travel[i];
            }
        }
        //paper
        for(int i = 0;i<=ends[1];i++){
            sum+=maps[i].getOrDefault('P',0);
            if(i<ends[1]){
                sum+=travel[i];
            }
        }
        //glass
        for(int i = 0;i<=ends[2];i++){
            sum+=maps[i].getOrDefault('G',0);
            if(i<ends[2]){
                sum+=travel[i];
            }
        }
        return sum;
    }
}
