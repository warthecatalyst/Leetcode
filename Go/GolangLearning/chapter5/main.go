package main

import (
	"fmt"
	"sync"
)

const zero = 0.0

func main() {
	var wg sync.WaitGroup
	intSlice := []int{1, 2, 3, 4, 5}
	wg.Add(len(intSlice))
	ans1, ans2 := 0, 0
	for _, v := range intSlice {
		vv := v
		go func() {
			//fmt.Printf("v = %v,vv=%v\n", v, vv)
			defer wg.Done()
			ans1 += v
			ans2 += vv
		}()
	}
	wg.Wait()
	fmt.Printf("ans1:%v,ans2:%v\n", ans1, ans2)

	myTest1()
}

func myTest() {
	defer fmt.Println("12345")
	panic("bad")
}

func myTest1() {
	var wg sync.WaitGroup
	intSlice := []int{1, 2, 3, 4, 5}
	wg.Add(len(intSlice))
	ans1, ans2 := 0, 0
	for _, v := range intSlice {
		vv := v
		go func() {
			//fmt.Printf("v = %v,vv=%v\n", v, vv)
			defer wg.Done()
			ans1 += v
			ans2 += vv
		}()
	}
	wg.Wait()
	fmt.Printf("ans1:%v,ans2:%v\n", ans1, ans2)
}
