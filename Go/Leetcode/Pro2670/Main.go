package main

import "fmt"

func distinctDifferenceArray(nums []int) []int {
	n := len(nums)
	suffix := make([]int, n+1)
	suffix[n] = 0
	mySet := make(map[int]int, 0)
	for i := n - 1; i >= 0; i-- {
		if _, ok := mySet[nums[i]]; !ok {
			suffix[i] = suffix[i+1] + 1
			mySet[nums[i]] = 1
		} else {
			suffix[i] = suffix[i+1]
		}
	}
	mySet = make(map[int]int, 0)
	prefix := make([]int, n+1)
	prefix[0] = 0
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		if _, ok := mySet[nums[i]]; !ok {
			prefix[i+1] = prefix[i] + 1
			mySet[nums[i]] = 1
		} else {
			prefix[i+1] = prefix[i]
		}
		ans[i] = prefix[i+1] - suffix[i+1]
	}
	return ans
}

func main() {
	ans := distinctDifferenceArray([]int{1, 2, 3, 4, 5})
	fmt.Println(ans)
}
