package Pro2185;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for(String word:words){
            if(word.startsWith(pref)){
                cnt++;
            }
        }
        return cnt;
    }
}
