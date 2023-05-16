package main

import "fmt"

func main() {
	myMap := map[string]interface{}{}
	myMap["abc"] = 1
	fmt.Println(myMap)
	changeMap(myMap)
	fmt.Println(myMap)

	intSlice := []int{1, 2, 3}
	fmt.Println(intSlice)
	changeSlice(intSlice)
	fmt.Println(intSlice)
	appendSlice(intSlice)
	fmt.Println(intSlice)
}

//在函数中改变一个map在主函数中也会改变
func changeMap(mtc map[string]interface{}) {
	mtc["efg"] = 2
}

func changeSlice(intSlice []int) {
	for i := range intSlice {
		intSlice[i] = 4
	}
}

func appendSlice(intSlice []int) {
	intSlice = append(intSlice, 0)
}
