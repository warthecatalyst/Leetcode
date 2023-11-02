package main

import "sort"

func filterRestaurants(restaurants [][]int, veganFriendly int, maxPrice int, maxDistance int) []int {
	ans := make([][]int, 0)
	for i := 0; i < len(restaurants); i++ {
		if veganFriendly == 0 || veganFriendly == 1 && restaurants[i][2] == 1 {
			if restaurants[i][3] <= maxPrice && restaurants[i][4] <= maxDistance {
				ans = append(ans, restaurants[i])
			}
		}
	}
	sort.Slice(ans, func(i, j int) bool {
		return ans[i][1] > ans[j][1] || ans[i][1] == ans[j][1] && ans[i][0] > ans[j][0]
	})
	res := make([]int, 0)
	for _, val := range ans {
		res = append(res, val[0])
	}
	return res
}
