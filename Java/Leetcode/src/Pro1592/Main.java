package Pro1592;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reorderSpaces("  this   is  a sentence ");
    }
}

class Solution {
    public String reorderSpaces(String text) {
        int length = text.length();
        String[] words = text.trim().split(" +");
        for(String word:words){
            System.out.println(word);
        }

        int cntSpace = length;
        for (String word : words) {
            cntSpace -= word.length();
        }
        StringBuilder sb = new StringBuilder();

        if (words.length == 1) {
            sb.append(words[0]);
            sb.append(" ".repeat(Math.max(0, cntSpace)));
            return sb.toString();
        }

        int perSpace = cntSpace / (words.length - 1);
        int restSpace = cntSpace % (words.length - 1);
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append(" ".repeat(Math.max(0, perSpace)));
            }
            sb.append(words[i]);
        }
        sb.append(" ".repeat(Math.max(0, restSpace)));
        return sb.toString();
    }
}
