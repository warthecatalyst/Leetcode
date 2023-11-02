package main

func main() {

}

func singleNumber(nums []int) []int {
	xorSum := 0
	for _, num := range nums {
		xorSum ^= num
	}
	idx := xorSum & -xorSum
	ans := make([]int, 2)
	for _, num := range nums {
		if num&idx == 0 {
			ans[0] ^= num
		} else {
			ans[1] ^= num
		}
	}
	return ans
}
