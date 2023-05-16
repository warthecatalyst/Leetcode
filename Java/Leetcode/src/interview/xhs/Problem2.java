package interview.xhs;

import java.util.*;

public class Problem2 {
    //题目描述：
    //实验室需要配制一种溶液。现在，研究员面前有n种该物质的溶液，每一种有无限多瓶，第i种的溶液体积为xi，里面含有yi单位的该物质。研究员每次可以选择一瓶溶液，将其倒入另外一瓶（假设瓶子的容量无限），即可以看作将两个瓶子内的溶液合并。此时合并的溶液体积和物质含量都等于之前两个瓶子内的之和。
    //
    //特别地，如果瓶子A与B的溶液体积相同，那么A与B合并之后该物质的含量会产生化学反应，使得该物质含量增加X单位。
    //
    //研究员的任务是配制溶液体积恰好等于C的，且尽量浓的溶液（即物质含量尽量多）。研究员想要知道物质含量最多是多少。
    //
    //
    //
    //输入描述
    //第一行三个正整数n,X,C；
    //
    //第二行n个正整数x1,x2,...,xn，中间用空格隔开；
    //
    //第三行n个正整数y1,y2,...,yn，中间用空格隔开。
    //
    //对于所有数据，1≤n,X,C,yi≤1000,1≤xi≤C
    //
    //数据保证至少存在一种方案能够配制溶液体积恰好等于C的溶液。
    //
    //输出描述
    //输出一行一个整数，表示答案。
    //
    //
    //样例输入
    //3 4 16
    //5 3 4
    //2 4 1
    //样例输出
    //29
    //
    //提示
    //一瓶溶液可以表示为 (体积,含量)，则初始三种溶液可以分别表示为 (5,2), (3,4), (4,1)
    static Scanner scanner = new Scanner(System.in);
    static int n,X,C;
    static class Element{
        int volume; //体积
        List<Integer> values;   //含量
        Element(int volume){
            this.volume = volume;
            this.values = new ArrayList<>();
        }
    }
    static Map<Integer,Element> elementMap;

    public static void main(String[] args) {
        int n,X,C;
        n = scanner.nextInt();
        X = scanner.nextInt();
        C = scanner.nextInt();
        elementMap = new HashMap<>();
        int[] volumes = new int[n];
        for(int i = 0;i<n;i++){
            volumes[i] = scanner.nextInt();
            elementMap.putIfAbsent(volumes[i],new Element(volumes[i]));
        }

        for(int i = 0;i<n;i++){
            int value = scanner.nextInt();
            int volume = volumes[i];
            elementMap.get(volume).values.add(value);
        }
        int[] dp = new int[C+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(Map.Entry<Integer,Element> entry:elementMap.entrySet()){
            int volume = entry.getKey();
            List<Integer> values = entry.getValue().values;
            for(int i = 1;i*volume<=C;i++){
                for(int value:values){
                    dp[i*volume] = Math.max(dp[i*volume],value*i+X*(i/2));
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[C]);
    }
}
