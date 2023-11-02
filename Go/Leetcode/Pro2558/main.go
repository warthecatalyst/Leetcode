package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	ans := pickGifts([]int{25, 64, 9, 4, 100}, 4)
	fmt.Println(ans)
}

func pickGifts(gifts []int, k int) int64 {
	n := len(gifts)
	for i := 0; i < k; i++ {
		sort.Ints(gifts)
		gifts[n-1] = int(math.Sqrt(float64(gifts[n-1])))
		// fmt.Println(gifts)
	}
	ans := int64(0)
	for _, val := range gifts {
		ans += int64(val)
	}
	return ans
}
