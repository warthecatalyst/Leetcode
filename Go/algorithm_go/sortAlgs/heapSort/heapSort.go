package main

import "fmt"

func heapSort(arr []int) {
	n := len(arr)
	for i := n/2 - 1; i >= 0; i-- {
		adjustHeap(arr, i, n)
		fmt.Println("i = ", i, "arr = ", arr)
	}
	for j := n - 1; j > 0; j-- {
		swap(arr, 0, j)
		adjustHeap(arr, 0, j)
		fmt.Println("j = ", j, "arr = ", arr)
	}
}

func adjustHeap(arr []int, i, length int) {
	temp := arr[i]
	for k := i*2 + 1; k < length; k = k*2 + 1 {
		if k+1 < length && arr[k+1] > arr[k] {
			k++
		}
		if arr[k] > temp { //如果较大就把子节点赋值给父节点
			arr[i] = arr[k]
			i = k
		} else {
			break
		}
	}
	arr[i] = temp //最后再赋值回去
}

func swap(arr []int, i, j int) {
	temp := arr[i]
	arr[i] = arr[j]
	arr[j] = temp
}

func main() {
	arr := []int{1, 3, 5, 6, 8, 2, 4, 7, 9}
	heapSort(arr)
	fmt.Println(arr)
}
