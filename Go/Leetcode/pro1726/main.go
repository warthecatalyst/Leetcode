package main

func main() {

}

func tupleSameProduct(nums []int) int {
	n := len(nums)
	cnt := make(map[int]int)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			cnt[nums[i]*nums[j]]++
		}
	}
	ans := 0
	for _, v := range cnt {
		ans += v * (v - 1) * 4
	}
	return ans
}
