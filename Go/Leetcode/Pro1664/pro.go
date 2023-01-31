package Pro1664

func waysToMakeFair(nums []int) int {
	var odd1, even1, odd2, even2 int
	for i, num := range nums {
		if i%2 == 0 {
			even2 += num
		} else {
			odd2 += num
		}
	}
	res := 0
	for i, num := range nums {
		if i%2 == 0 {
			even2 -= num
		} else {
			odd2 -= num
		}
		if odd1+even2 == odd2+even1 {
			res++
		}
		if i&1 > 0 {
			odd1 += num
		} else {
			even1 += num
		}
	}
	return res
}
