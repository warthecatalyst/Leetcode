package main

func main() {

}

var movements = [][]int{{-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {2, 1}, {2, -1}}

func checkValidGrid(grid [][]int) bool {
	n := len(grid)
	startx, starty := -1, -1
	for i := 0; i < n && startx == -1; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				startx = i
				starty = j
				break
			}
		}
	}
	cnt := 0
	for cnt < n*n-1 {
		flag := false
		for _, movement := range movements {
			newx, newy := startx+movement[0], starty+movement[1]
			if isIn(newx, newy, n) && grid[newx][newy] == cnt+1 {
				flag = true
				startx = newx
				starty = newy
				break
			}
		}
		if flag {
			cnt++
		} else {
			return false
		}
	}
	return true
}

func isIn(x, y, n int) bool {
	return x >= 0 && x < n && y >= 0 && y < n
}
