package main

import "fmt"

func main() {
	arr := []int{9, 8, 6, 7, 4, 5, 1, 2, 3}
	heapSort(arr)
	fmt.Println(arr)
}

func heapSort(arr []int) {
	arrlength := len(arr)
	// 构建大顶堆
	for i := arrlength/2 - 1; i >= 0; i-- {
		adjustHeap(arr, i, arrlength)
	}
	// 调整堆结构，交换堆顶元素与末尾元素
	for j := arrlength - 1; j > 0; j-- {
		swap(arr, 0, j)
		adjustHeap(arr, 0, j)
	}
}

func adjustHeap(arr []int, i int, length int) {
	temp := arr[i] //取出当前元素i
	for k := i*2 + 1; k < length; k = k*2 + 1 {
		//从i结点的左子结点开始，也就是2i+1处开始
		if k+1 < length && arr[k] < arr[k+1] { //如果左子结点小于右子结点，k指向右子结点
			k++
		}
		if arr[k] > temp { //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
			arr[i] = arr[k]
			i = k
		} else {
			break
		}
	}
	arr[i] = temp //将temp放到最终的位置
}

func swap(arr []int, a, b int) {
	temp := arr[a]
	arr[a] = arr[b]
	arr[b] = temp
}
