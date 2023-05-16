package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

var (
	firstDone  int32 = 0
	secondDone int32 = 0
	wg         sync.WaitGroup
)

func printFirst() {
	fmt.Println("first")
	atomic.AddInt32(&firstDone, 1)
	wg.Done()
}

func printSecond() {
	for firstDone != 1 {

	}
	fmt.Println("second")
	atomic.AddInt32(&secondDone, 1)
	wg.Done()
}

func printThird() {
	for secondDone != 1 {

	}
	fmt.Println("third")
	wg.Done()
}

func main() {
	go printFirst()
	go printSecond()
	go printThird()
	wg.Add(3)
	wg.Wait()
}
