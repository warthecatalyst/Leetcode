package Pro1668;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maxRepeating(String sequence, String word) {
        int ans = 0;
        int max_l = sequence.length()/word.length();
        for(int i = 1;i<=max_l;i++){
            String word_rep = word.repeat(i);
            if(sequence.contains(word_rep)){
                ans = i;
            }else{
                break;
            }
        }
        return ans;
    }
}
