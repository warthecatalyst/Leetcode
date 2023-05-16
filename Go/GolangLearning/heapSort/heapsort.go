package main

import "fmt"

func main() {
	arr := []int{1, 3, 5, 9, 8, 2, 4, 7, 6}
	heapSort(arr)
	fmt.Println(arr)
}

func heapSort(arr []int) []int {
	arrLen := len(arr)
	for i := arrLen/2 - 1; i >= 0; i-- {
		heapify(arr, i, arrLen)
	}
	for i := arrLen - 1; i > 0; i-- {
		swap(arr, 0, i)
		heapify(arr, 0, i)
	}
	return arr
}

func heapify(arr []int, i, arrLen int) {
	temp := arr[i]
	for k := i*2 + 1; k < arrLen; k = k*2 + 1 {
		if k+1 < arrLen && arr[k+1] > arr[k] {
			k++
		}
		if arr[k] > temp {
			arr[i] = arr[k]
			i = k
		} else {
			break
		}
	}
	arr[i] = temp
}

func swap(arr []int, i, j int) {
	arr[i], arr[j] = arr[j], arr[i]
}
