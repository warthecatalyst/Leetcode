package Pro1662;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder words1 = new StringBuilder();
        StringBuilder words2 = new StringBuilder();
        for(String word:word1){
            words1.append(word);
        }
        for(String word:word2){
            words2.append(word);
        }
        return words1.toString().equals(words2.toString());
    }
}
