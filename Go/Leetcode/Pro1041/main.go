package main

var (
	directions = [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
)

func isRobotBounded(instructions string) bool {
	directIndex := 0
	x, y := 0, 0
	for _, instruction := range instructions {
		if instruction == 'G' {
			x += directions[directIndex][0]
			y += directions[directIndex][1]
		} else if instruction == 'R' {
			directIndex = (directIndex + 1) % 4
		} else if instruction == 'L' {
			directIndex = (directIndex + 3) % 4
		}
	}
	return directIndex != 0 || (x == 0 && y == 0)
}
