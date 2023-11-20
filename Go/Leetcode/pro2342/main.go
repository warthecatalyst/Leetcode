package main

func main() {

}

func maximumSum(nums []int) int {
	large := make([][]int, 100)
	for i := 0; i < 100; i++ {
		large[i] = []int{0, 0}
	}
	maxSum := -1
	for _, num := range nums {
		tmp, sum := num, 0
		for tmp > 0 {
			sum += tmp % 10
			tmp /= 10
		}
		if num >= large[sum][0] {
			large[sum][1] = large[sum][0]
			large[sum][0] = num
		} else if num > large[sum][1] {
			large[sum][1] = num
		}
		if large[sum][0] > 0 && large[sum][1] > 0 {
			maxSum = max(maxSum, large[sum][0]+large[sum][1])
		}
	}
	return maxSum
}

func max(a, b int) int {
	if a >= b {
		return a
	}
	return b
}
