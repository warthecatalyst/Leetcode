package interview.PDD.Autumn.Pro2;

import java.util.*;
import java.io.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int N,M;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        processInput(input);
        nums = new int[N];
        input = bufferedReader.readLine();
        processInput2(input);
        Arrays.sort(nums);
        int i = 0, j = N/2;
        int count = 0;
        while(i < N/2 && j < N) {
            if(nums[j] - nums[i] >= M) {
                i++;
                j++;
                count++;
            } else {
                j++;
            }
        }
        System.out.println(count);
        bufferedReader.close();
    }

    static void processInput(String line) {
        String[] strs = line.split(" ");
        N = Integer.parseInt(strs[0]);
        M = Integer.parseInt(strs[1]);
    }

    static void processInput2(String line) {
        String[] strs = line.split(" ");
        for(int i = 0;i < N;i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
    }
}
