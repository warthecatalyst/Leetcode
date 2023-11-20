package Pro318;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                masks[i] |= 1 << (c - 'a');
            }
        }
        int maxProd = 0;
        for(int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }
}
