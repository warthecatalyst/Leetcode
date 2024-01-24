package interview.Huawei;

import java.util.Stack;

public class EncodedString {
    public static void main(String[] args) {
        System.out.println(encodeString("abc3[cd]xyz"));
    }

    private static String encodeString(String string) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> stackInt = new Stack<>();
        Stack<String> stackString = new Stack<>();
        stackString.add("");
        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if(Character.isDigit(c)) {
                int tmp = c-'0';
                for (int j = i + 1; j < string.length() && Character.isDigit(string.charAt(j)); j++) {
                    tmp = tmp * 10 + string.charAt(j)-'0';
                }
                stackInt.add(tmp);
            } else if (c == '[') {
                stackString.add(res.toString());
                res = new StringBuilder();
            } else if (c == ']') {
                int times = stackInt.pop();
                res = new StringBuilder(stackString.pop()).append(res.toString().repeat(times));
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
