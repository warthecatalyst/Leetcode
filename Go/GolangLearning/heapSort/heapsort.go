package main

import "fmt"

func main() {
	arr := []int{1, 3, 5, 9, 8, 2, 4, 7, 6}
	heapSort(arr)
	fmt.Println(arr)
}

func heapSort(arr []int) []int {
	// 构建大顶堆
	arrLen := len(arr)
	for i := arrLen/2 - 1; i >= 0; i-- {
		// 从第一个非叶子节点从下至上，从左至右调整结构
		heapify(arr, i, arrLen)
	}
	// 调整堆结构+交换堆顶和末尾元素
	for j := arrLen - 1; j > 0; j-- {
		swap(arr, 0, j)
		heapify(arr, 0, j)
	}
	return arr
}

func heapify(arr []int, i, arrLen int) {
	temp := arr[i]
	for k := i*2 + 1; k < arrLen; k = k*2 + 1 {
		if k+1 < arrLen && arr[k] < arr[k+1] {
			k++
		}
		// 假设子节点大于父节点
		if arr[k] > temp {
			arr[i] = arr[k]
			i = k
		} else {
			break
		}
	}
	arr[i] = temp //把temp值放到最终的位置
}

func swap(arr []int, i, j int) {
	arr[i], arr[j] = arr[j], arr[i]
}
