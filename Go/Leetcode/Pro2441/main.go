package main

func findMaxK(nums []int) int {
	setCnt := map[int]interface{}{}
	for _, num := range nums {
		setCnt[num] = true
	}
	maxKey := -1
	for key := range setCnt {
		if key > 0 && key > maxKey {
			_, ok := setCnt[-key]
			if ok {
				maxKey = key
			}
		}
	}
	return maxKey
}

func main() {

}
