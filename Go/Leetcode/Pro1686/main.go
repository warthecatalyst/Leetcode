package main

import "sort"

func stoneGameVI(aliceValues []int, bobValues []int) int {
	n := len(aliceValues)
	values := make([][]int, n)
	for i := 0; i < n; i++ {
		values[i] = []int{aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]}
	}
	sort.Slice(values, func(i, j int) bool {
		return values[i][0] > values[j][0]
	})
	aliceSum, BobSum := 0, 0
	for i := 0; i < n; i++ {
		if i%2 == 0 {
			aliceSum += values[i][1]
		} else {
			BobSum += values[i][2]
		}
	}

	if aliceSum > BobSum {
		return 1
	} else if aliceSum == BobSum {
		return 0
	} else {
		return -1
	}
}
