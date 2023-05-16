package main

import "fmt"

func mostFrequentEven(nums []int) int {
	evenCount := make(map[int]int, 0)
	for _, num := range nums {
		if num%2 == 0 {
			evenCount[num]++
		}
	}
	maxCnt := 0
	ans := -1
	fmt.Println(evenCount)
	for k, v := range evenCount {
		if v > maxCnt {
			maxCnt = v
			ans = k
		} else if v == maxCnt && k < ans {
			ans = k
		}
	}
	return ans
}

func main() {
	ans := mostFrequentEven([]int{0, 1, 2, 2, 4, 4, 1})
	fmt.Println(ans)
}
