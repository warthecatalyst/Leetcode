package main

import (
	"fmt"
	"time"
)

func main() {
	ch := make(chan float64)
	go func() {
		// 从3循环到0
		for i := 5; i >= 2; i-- {
			// 发送3到0之间的数值
			ch <- float64(i)
			// 每次发送完时等待
			time.Sleep(time.Second)
		}
	}()

	// 遍历接收通道数据
	for data := range ch {
		// 打印通道数据
		fmt.Println(data)
		// 当遇到数据0时, 退出接收循环
		if data < 3 {
			break
		}
	}
	close(ch)
	// ch <- 1 //往关闭通道中写,会直接panic
	data := <-ch //从关闭通道中读，会读到默认值
	fmt.Println(data)
}
