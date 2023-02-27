package main

import "fmt"

func main() {
	myMap := map[string]interface{}{}
	myMap["abc"] = 1
	fmt.Println(myMap)
	changeMap(myMap)
	fmt.Println(myMap)
}

//在函数中改变一个map在主函数中也会改变
func changeMap(mtc map[string]interface{}) {
	mtc["efg"] = 2
}
