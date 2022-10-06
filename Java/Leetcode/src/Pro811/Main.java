package Pro811;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> ans = new ArrayList<>();
        Map<String,Integer> domainMap = new HashMap<>();
        for(String domain:cpdomains){
            int idx = domain.indexOf(' ');
            int cnt = Integer.parseInt(domain.substring(0,idx));
            String dm = domain.substring(idx+1);
            domainMap.put(dm,domainMap.getOrDefault(dm,0)+cnt);
            for(idx = dm.indexOf('.');idx!=-1;){
                String fdm = dm.substring(idx+1);
                domainMap.put(fdm,domainMap.getOrDefault(fdm,0)+cnt);
                idx = dm.indexOf('.',idx+1);
            }
        }
        for(Map.Entry<String,Integer> entry:domainMap.entrySet()){
            ans.add(entry.getValue()+" "+entry.getKey());
        }
        return ans;
    }
}