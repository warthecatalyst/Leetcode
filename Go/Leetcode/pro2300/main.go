package main

import (
	"fmt"
	"sort"
)

func main() {
	ans := successfulPairs([]int{5, 1, 3}, []int{1, 2, 3, 4, 5}, 7)
	fmt.Println(ans)
}

func successfulPairs(spells []int, potions []int, success int64) []int {
	sort.Ints(potions)
	n := len(spells)
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		//potions二分找到比success/spell更大的数
		idx := sort.Search(len(potions), func(j int) bool {
			return int64(potions[j])*int64(spells[i]) >= success
		})
		ans[i] = len(potions) - idx
	}
	return ans
}

func binarySearch(potions []int, spell int, success int64) int {
	//fmt.Println(spell)
	m := len(potions)
	if int64(spell)*int64(potions[m-1]) < success {
		return 0
	}
	left, right := 0, m-1
	for left < right {
		mid := (left + right) / 2
		//fmt.Println("left = ", left, " ,right = ", right, " ,mid = ", mid)
		if int64(spell)*int64(potions[mid]) >= success {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return m - right
}
