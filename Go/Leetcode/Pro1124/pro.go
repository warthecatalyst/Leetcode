package main

import "fmt"

func longestWPI(hours []int) int {
	n := len(hours)
	res := 0
	busyday := make([]int, n+1)
	for i := 1; i <= n; i++ {
		if hours[i-1] > 8 {
			busyday[i] = busyday[i-1] + 1
		} else {
			busyday[i] = busyday[i-1]
		}
	}
	//fmt.Println(busyday)
	for i := 0; i < n; i++ {
		busyi := busyday[i]
		leisurei := i - busyi
		for j := i + 1; j <= n; j++ {
			busyj := busyday[j]
			leisurej := j - busyj
			//fmt.Printf("i = %v, busyi = %v, leisurei = %v, j = %v, busyj = %v, leisurej = %v\n", i, busyi, leisurei, j, busyj, leisurej)
			if busyj-busyi > leisurej-leisurei {
				res = max(res, j-i)
			}
		}
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func main() {
	hours := []int{9, 9, 6, 0, 6, 6, 9}
	ans := longestWPI(hours)
	fmt.Println(ans)
}
