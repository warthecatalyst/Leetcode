package main

import "sort"

func main() {

}

func sumDistance(nums []int, s string, d int) int {
	const mod int = 1e9 + 7
	newPositions := make([]int, len(nums))
	for i := 0; i < len(nums); i++ {
		if s[i] == 'L' {
			newPositions[i] = nums[i] - d
		} else {
			newPositions[i] = nums[i] + d
		}
	}
	sort.Slice(newPositions, func(i, j int) bool {
		return newPositions[i] < newPositions[j]
	})
	res := 0
	for i := 1; i < len(nums); i++ {
		res += (newPositions[i] - newPositions[i-1]) * i % mod * (len(nums) - i) % mod
		res %= mod
	}
	return res
}
