package Pro2525;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
        boolean heavy = mass >= 100;
        long size = (long) length * width * height;
        boolean bulky = length >= 10000 || width >= 10000 || height >= 10000 || size >= 1000000000;
        if (heavy && bulky) {
            return "Both";
        } else if (heavy) {
            return "Heavy";
        } else if (bulky) {
            return "Bulky";
        }
        return "Neither";
    }
}
