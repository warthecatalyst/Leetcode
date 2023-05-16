package main

import "fmt"

func main() {
	for i := 5; i <= 15; i++ {
		ans := searchRange([]int{5, 7, 7, 8, 8, 10}, i)
		fmt.Println("i = ", i, "ans = ", ans)
	}
}

func searchRange(nums []int, target int) []int {
	//找到左边界和右边界
	n := len(nums)
	left, right := 0, n-1
	leftRange := n
	for left <= right {
		mid := (left + right) / 2
		if nums[mid] >= target {
			right = mid - 1
			leftRange = mid
		} else {
			left = mid + 1
		}
	}
	left, right = 0, n-1
	rightRange := n
	for left <= right {
		mid := (left + right) / 2
		if nums[mid] > target {
			right = mid - 1
			rightRange = mid
		} else {
			left = mid + 1
		}
	}
	rightRange--
	if leftRange <= rightRange && rightRange < n && nums[leftRange] == target && nums[rightRange] == target {
		return []int{leftRange, rightRange}
	}
	return []int{-1, -1}
}
