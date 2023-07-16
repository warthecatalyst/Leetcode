package forInter.NiukeCompetition2.Pro1;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static RecommendationSystem recommendationSystem;
    public static void main(String[] args) {
        recommendationSystem = new RecommendationSystem();
        while(true){
            String line = scanner.nextLine();
            if("-1".equals(line)){
                break;
            }else{
                String[] inputs = line.split("\\t");
                recommendationSystem.addUserScore(inputs[0],inputs[1],Double.parseDouble(inputs[2]));
            }
        }
        Map<String,List<String>> result = recommendationSystem.getRecommendations();
        for(Map.Entry<String,List<String>> entry:result.entrySet()){
            String user = entry.getKey();
            for(String commodity:entry.getValue()){
                System.out.println(user+"\t"+commodity);
            }
        }
    }
}


/**
 * 基于协同过滤的用户推荐算法类
 */
class RecommendationSystem{
    /**
     * 用户
     */
    Set<String> users;

    /**
     * 商品
     */
    Set<String> commodities;

    /**
     * 用户对商品的打分表
     */
    Map<String,Map<String,Double>> userScores;

    /**
     * 商品的购买表
     */
    Map<String,Set<String>> commoditiesBought;

    public RecommendationSystem(){
        users = new HashSet<>();
        commodities = new HashSet<>();
        userScores = new HashMap<>();
        commoditiesBought = new HashMap<>();
    }

    /**
     * 添加一个用户对商品的评分
     * @param user 用户id
     * @param commodity 商品id
     * @param value 评分
     */
    public void addUserScore(String user,String commodity,double value){
        users.add(user);
        commodities.add(commodity);
        //向用户评分表中插入打分结果
        Map<String,Double> userScore = userScores.getOrDefault(user,new HashMap<>());
        userScore.put(commodity,value);
        userScores.put(user,userScore);
        //向商品评分表中插入购买用户
        Set<String> commodityBought = commoditiesBought.getOrDefault(commodity,new HashSet<>());
        commodityBought.add(user);
        commoditiesBought.put(commodity,commodityBought);
    }

    /**
     * 计算商品A和商品B之间的相似度
     * @param commodityA 商品A
     * @param commodityB 商品B
     * @return 相似度
     */
    private double calculateSimilarityBetweenCommodities(String commodityA,String commodityB){
        //找到同时购买商品A和商品B的用户
        Set<String> bothBoughtUsers = new HashSet<>(commoditiesBought.get(commodityA));
        bothBoughtUsers.retainAll(commoditiesBought.get(commodityB));
        double part1 = 0,part2 = 0,part3 = 0;
        for(String user:bothBoughtUsers){
            double aValue = userScores.get(user).get(commodityA);
            double bValue = userScores.get(user).get(commodityB);
            part1 += aValue*bValue;
            part2 += aValue*aValue;
            part3 += bValue*bValue;
        }
        if(part1==0){
            return 0;
        }else{
            return part1/(Math.sqrt(part2)*Math.sqrt(part3));
        }
    }

    /**
     * 获取用户的推荐结果
     * @return 用户的推荐结果: 用户->被推荐的商品(用户id升序，商品id升序)
     */
    public Map<String,List<String>> getRecommendations(){
        Map<String,List<String>> result = new TreeMap<>();
        for(String user:users){
            List<String> recommendedCommodities = new ArrayList<>();
            //用户有哪些商品没买
            Set<String> notBought = new HashSet<>(commodities);
            notBought.removeAll(userScores.get(user).keySet());
            //对每个没购买的商品计算喜爱度
            for(String commodity:notBought){
                double totalLike = 0;
                for(Map.Entry<String,Double> entry:userScores.get(user).entrySet()){
                    double similarity = calculateSimilarityBetweenCommodities(commodity,entry.getKey());
                    totalLike += similarity*entry.getValue();
                }
                if(totalLike>1){
                    recommendedCommodities.add(commodity);
                }
            }
            Collections.sort(recommendedCommodities);
            result.put(user,recommendedCommodities);
        }
        return result;
    }

}
