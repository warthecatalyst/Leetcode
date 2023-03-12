package Pro1487;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String[] getFolderNames(String[] names) {
        int n = names.length;
        String[] ans = new String[n];
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i<n;i++){
            String name = names[i];
            if(!map.containsKey(name)){
                map.put(name,1);
                ans[i] = name;
            }else{
                int k = map.get(name);
                while(map.containsKey(addSuffix(name,k))){
                    k++;
                }
                ans[i] = addSuffix(name, k);
                map.put(name, k + 1);
                map.put(addSuffix(name, k), 1);
            }
        }
        return ans;
    }

    String addSuffix(String name,int k){
        return name + '(' + k + ')';
    }
}
