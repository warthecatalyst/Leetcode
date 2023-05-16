package main

func hardestWorker(n int, logs [][]int) int {
	maxTime, minId := logs[0][1], logs[0][0]
	for i := 1; i < len(logs); i++ {
		time := logs[i][1] - logs[i-1][1]
		if time > maxTime {
			maxTime = time
			minId = logs[i][0]
		} else if time == maxTime && logs[i][0] < minId {
			minId = logs[i][0]
		}
	}
	return minId
}

func main() {

}
