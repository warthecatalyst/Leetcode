package Pro2586;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        for(int i = left; i <= right; i++) {
            String word = words[i];
            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c =='o' || c == 'u';
    }
}
