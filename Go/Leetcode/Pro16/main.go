package main

import (
	"math"
	"sort"
)

func threeSumClosest(nums []int, target int) int {
	sort.Ints(nums)
	var (
		n    = len(nums)
		best = math.MaxInt32
	)

	// 根据差值的绝对值来更新答案
	update := func(cur int) {
		if abs(cur-target) < abs(best-target) {
			best = cur
		}
	}

	for first := 0; first < n; first++ {
		if first > 0 && nums[first] == nums[first-1] {
			continue
		}
		second := first + 1
		third := n - 1
		for second < third {
			sum := nums[first] + nums[second] + nums[third]
			if sum == target {
				return target
			}
			update(sum)
			if sum > target {
				third1 := third - 1
				for third1 > second && nums[third1] == nums[third] {
					third1--
				}
				third = third1
			} else {
				second1 := second + 1
				for second1 < third && nums[second1] == nums[second] {
					second1++
				}
				second = second1
			}
		}
	}
	return best
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
