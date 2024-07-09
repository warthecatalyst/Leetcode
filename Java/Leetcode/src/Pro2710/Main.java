package Pro2710;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String removeTrailingZeros(String num) {
        int index = num.length() - 1;
        for (; index >= 0; index--) {
            if (num.charAt(index) != '0') {
                break;
            }
        }
        return num.substring(0, index + 1);
    }
}
