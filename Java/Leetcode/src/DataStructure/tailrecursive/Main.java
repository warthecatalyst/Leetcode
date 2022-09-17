package DataStructure.tailrecursive;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,3,3,4};
        int size = arr.length;
        System.out.println(numDup(arr,size-1,size,2));
    }

    private static int numDup(int[] arr,int start,int size,int element){
        if(start==0){
            return arr[0]==element?1:0;
        }
        return (arr[start]==element?1:0)+numDup(arr,start-1,size,element);
    }
}


