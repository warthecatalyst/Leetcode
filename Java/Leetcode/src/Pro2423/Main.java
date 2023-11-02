package Pro2423;

import java.util.*;
public class Main {
    public static void main(String[] args) {
    }
}

class Solution {
    public boolean equalFrequency(String word) {
        int[] frequency = new int[26];
        for (char c : word.toCharArray()) {
            frequency[c-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (frequency[i] == 0) {
                continue;
            }
            frequency[i]--;
            HashSet<Integer> freq = new HashSet<>();
            for (int f : frequency) {
                if (f > 0) {
                    freq.add(f);
                }
            }
            if (freq.size() == 1) {
                return true;
            }
            frequency[i]++;
        }
        return false;
    }
}
