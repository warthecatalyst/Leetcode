package Pro777;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public boolean canTransform(String start, String end) {
        if(start.length()!=end.length()){
            return false;
        }
        if(!start.replaceAll("X","").equals(end.replaceAll("X",""))){
            return false;
        }
        for (int i = 0, j = 0; i < start.length(); ++i) {
            if (start.charAt(i) == 'X') continue;
            while (end.charAt(j) == 'X') ++j;
            if (i != j && (start.charAt(i) == 'L') != (i > j)) return false;
            ++j;
        }
        return true;
    }
}