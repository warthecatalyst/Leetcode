package main

import "fmt"

type Video struct {
	videoId int64
}

func addList(videoList *[]*Video) {
	*videoList = append(*videoList, &Video{videoId: 1})
	*videoList = append(*videoList, &Video{videoId: 2})
	*videoList = append(*videoList, &Video{videoId: 3})
}

func main() {
	videoList := make([]*Video, 0)
	videoList = append(videoList, &Video{videoId: 1})
	for _, video := range videoList {
		fmt.Print(video.videoId, "\t")
	}
	fmt.Println()
	addList(&videoList)
	for _, video := range videoList {
		fmt.Print(video.videoId, "\t")
	}
}
