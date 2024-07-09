package Pro2288;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String discountPrices(String sentence, int discount) {
        String[] strs = sentence.split(" ");
        for (int i = 0; i < strs.length; i++) {
            String word = strs[i];
            if (word.charAt(0) == '$' && isNumeric(word.substring(1))) {
                double price = Long.parseLong(word.substring(1)) * (1 - discount / 100.0);
                strs[i] = String.format("$%.2f", price);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    private boolean isNumeric(String str) {
        if (str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
