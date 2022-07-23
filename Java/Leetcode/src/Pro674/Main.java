package Pro674;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] strings = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(String str:strings){
            String tmp = str;
            for(String root:dictionary){
                if(str.startsWith(root)&&root.length()<tmp.length()){
                    tmp = root;
                }
            }
            stringBuilder.append(tmp).append(' ');
        }
        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
        return stringBuilder.toString();
    }
}
