package LeetcodeR2;

import java.util.*;
public class Main {

}

class MyHashMap{
    private static final int MAP_SIZE_DEFAULT = 16;
    private int mapSize;
    private List<Object[]>[] buckets; //链表解决hash冲突
    public MyHashMap(){
        mapSize = MAP_SIZE_DEFAULT;
        buckets = new List[mapSize];
        for(int i = 0;i<mapSize;i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public MyHashMap(int mapSize){
        this.mapSize = mapSize;
        buckets = new List[mapSize];
        for(int i = 0;i<mapSize;i++){
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(Object key,Object value){
        int hashcode = key.hashCode();
        hashcode = hashcode % mapSize;
        boolean isContain = false;
        for(int i = 0;i<buckets[hashcode].size();i++) { //如果当前已经有了就替换
            Object[] keyAndValue = buckets[hashcode].get(i);
            Object objKey = keyAndValue[0];
            if(key.equals(objKey)){
                isContain = true;
                buckets[hashcode].set(i,new Object[]{key,value});
            }
        }
        if(!isContain){ //没有就在bucket中添加
            buckets[hashcode].add(new Object[]{key,value});
        }
    }

    public Object get(Object key){
        int hashcode = key.hashCode();
        hashcode = hashcode%mapSize;
        for(Object[] keyAndValue:buckets[hashcode]){
            Object objKey = keyAndValue[0], objValue = keyAndValue[1];
            if(objKey.equals(key)){
                return objValue;
            }
        }
        return null;
    }

    public boolean delete(Object key){
        int hashcode = key.hashCode();
        hashcode = hashcode%mapSize;
        for(int i = 0;i<buckets[hashcode].size();i++) { //如果当前已经有了就替换
            Object[] keyAndValue = buckets[hashcode].get(i);
            Object objKey = keyAndValue[0];
            if (key.equals(objKey)) {
                //删除当前项，return true
                buckets[hashcode].remove(i);
                return true;
            }
        }
        return false;
    }
}
