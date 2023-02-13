package Pro1604;

import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> timeMap = new HashMap<String, List<Integer>>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            timeMap.putIfAbsent(name, new ArrayList<Integer>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            timeMap.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        for(String name:timeMap.keySet()){
            List<Integer> times = timeMap.get(name);
            times.sort(Integer::compareTo);
            for(int i = 2;i<times.size();i++){
                int time1 = times.get(i-2), time2 = times.get(i);
                if(time2-time1<=60){
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
