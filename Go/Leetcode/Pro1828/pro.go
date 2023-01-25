package Pro1828

func countPoints(points [][]int, queries [][]int) []int {
	ans := make([]int, len(queries))
	for i, q := range queries {
		x, y, r := q[0], q[1], q[2]
		for _, p := range points {
			if (p[0]-x)*(p[0]-x)+(p[1]-y)*(p[1]-y) <= r*r {
				ans[i]++
			}
		}
	}
	return ans
}
