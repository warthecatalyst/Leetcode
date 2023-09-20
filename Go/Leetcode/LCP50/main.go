package main

import "math"

func giveGem(gem []int, operations [][]int) int {
	for _, operation := range operations {
		from, to := operation[0], operation[1]
		give := gem[from] / 2
		gem[from] -= give
		gem[to] += give
	}
	minVal := math.MaxInt32
	maxVal := 0
	for _, val := range gem {
		minVal = min(minVal, val)
		maxVal = max(maxVal, val)
	}
	return maxVal - minVal
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	return a + b - min(a, b)
}
