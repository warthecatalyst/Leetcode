package main

func twoSum(nums []int, target int) []int {
	cnt := make(map[int]int, 0)
	for i, v := range nums {
		value, ok := cnt[target-v]
		if ok {
			return []int{value, i}
		}
		cnt[v] = i
	}
	return []int{0, 0}
}

func main() {

}
