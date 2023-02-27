package main

func numberOfPairs(nums []int) []int {
	cnt := make(map[int]int)
	res := []int{0, 0}
	for _, num := range nums {
		cnt[num]++
		if cnt[num] == 2 {
			res[0]++
			cnt[num] = 0
		}
	}
	for _, v := range cnt {
		if v > 0 {
			res[1]++
		}
	}
	return res
}

func main() {

}
