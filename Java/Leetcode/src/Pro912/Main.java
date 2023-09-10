package Pro912;

import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
}

class QuickSort {
    Random random = new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums,0, nums.length-1);
        return nums;
    }

    public void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int pivotIndex = doublePointerSwap(arr, startIndex, endIndex);

        // 用分界值下标区分出左右区间，进行递归调用
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    private int doublePointerSwap(int[] arr, int startIndex, int endIndex) {
        int pivotIdx = startIndex + random.nextInt(endIndex-startIndex+1);
        swap(arr, startIndex, pivotIdx);
        int pivot = arr[startIndex];
        int leftPoint = startIndex;
        int rightPoint = endIndex;

        while (leftPoint < rightPoint) {
            // 从右向左找出比pivot小的数据
            while (leftPoint < rightPoint
                    && arr[rightPoint] > pivot) {
                rightPoint--;
            }
            // 从左向右找出比pivot大的数据
            while (leftPoint < rightPoint
                    && arr[leftPoint] <= pivot) {
                leftPoint++;
            }
            // 没有过界则交换
            if (leftPoint < rightPoint) {
                swap(arr, leftPoint, rightPoint);
            }
        }
        // 最终将分界值与当前指针数据交换
        arr[startIndex] = arr[rightPoint];
        arr[rightPoint] = pivot;
        // 返回分界值所在下标
        return rightPoint;
    }

    private void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class HeapSort {
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    public void heapSort(int[] nums) {
        int len = nums.length - 1;
        buildMaxHeap(nums, len);
        for (int i = len; i >= 1; --i) {
            swap(nums, i, 0);
            len -= 1;
            maxHeapify(nums, 0, len);
        }
    }

    public void buildMaxHeap(int[] nums, int len) {
        for (int i = len / 2; i >= 0; --i) {
            maxHeapify(nums, i, len);
        }
    }

    public void maxHeapify(int[] nums, int i, int len) {
        for (; (i << 1) + 1 <= len;) {
            int lson = (i << 1) + 1;
            int rson = (i << 1) + 2;
            int large;
            if (lson <= len && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson <= len && nums[rson] > nums[large]) {
                large = rson;
            }
            if (large != i) {
                swap(nums, i, large);
                i = large;
            } else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


