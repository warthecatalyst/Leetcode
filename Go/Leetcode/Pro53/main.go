package main

import "math"

func main() {

}

func maxSubArray(nums []int) int {
	//连续子数组的最大和
	curSum := 0
	ans := math.MinInt
	for _, num := range nums {
		ans = max(ans, num)
		if curSum+num < 0 {
			curSum = 0
		} else {
			curSum += num
		}

		if curSum > 0 {
			ans = max(ans, curSum)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
