package Pro2325;


import java.util.*;
public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String decodeMessage(String key, String message) {
        char c = 'a';
        Map<Character,Character> characterCharacterMap = new HashMap<>();
        for(char c1:key.toCharArray()){
            if(c1==' '){
                continue;
            }
            if(!characterCharacterMap.containsKey(c1)){
                characterCharacterMap.put(c1,c);
                c++;
            }
        }
//        for(Map.Entry<Character,Character> entry:characterCharacterMap.entrySet()){
//            System.out.println(entry.getKey()+"->"+entry.getValue());
//        }
        StringBuilder stringBuilder = new StringBuilder();
        for(char c1:message.toCharArray()){
            if(c1 == ' '){
                stringBuilder.append(' ');
            }else{
                stringBuilder.append(characterCharacterMap.get(c1));
            }
        }
        return stringBuilder.toString();
    }
}
