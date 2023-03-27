package main

type TrieNode struct {
	children []*TrieNode
	isEnd    bool
	fail     *TrieNode
}

func GetTrieNode() *TrieNode {
	return &TrieNode{
		children: make([]*TrieNode, 26),
		isEnd:    false,
		fail:     nil,
	}
}

func (t *TrieNode) getChild(index int) *TrieNode {
	return t.children[index]
}

func (t *TrieNode) setChild(index int, node *TrieNode) {
	t.children[index] = node
}

func (t *TrieNode) getIsEnd() bool {
	return t.isEnd
}

func (t *TrieNode) setIsEnd(b bool) {
	t.isEnd = b
}

func (t *TrieNode) getFail() *TrieNode {
	return t.fail
}

func (t *TrieNode) setFail(fail *TrieNode) {
	t.fail = fail
}

type StreamChecker struct {
	root, temp *TrieNode
}

func Constructor(words []string) StreamChecker {
	streamChecker := StreamChecker{}
	streamChecker.root = GetTrieNode()
	for _, word := range words {
		cur := streamChecker.root
		for i := range word {
			index := int(word[i] - 'a')
			if cur.getChild(index) == nil {
				cur.setChild(index, GetTrieNode())
			}
			cur = cur.getChild(index)
		}
		cur.setIsEnd(true)
	}
	streamChecker.root.setFail(streamChecker.root)
	queue := make([]*TrieNode, 0)
	for i := 0; i < 26; i++ {
		if streamChecker.root.getChild(i) != nil {
			streamChecker.root.getChild(i).setFail(streamChecker.root)
			queue = append(queue, streamChecker.root.getChild(i))
		} else {
			streamChecker.root.setChild(i, streamChecker.root)
		}
	}
	for len(queue) > 0 {
		node := queue[0]
		queue = queue[1:]
		node.setIsEnd(node.getIsEnd() || node.getFail().getIsEnd())
		for i := 0; i < 26; i++ {
			if node.getChild(i) != nil {
				node.getChild(i).setFail(node.getFail().getChild(i))
				queue = append(queue, node.getChild(i))
			} else {
				node.setChild(i, node.getFail().getChild(i))
			}
		}
	}
	streamChecker.temp = streamChecker.root
	return streamChecker
}

func (sc *StreamChecker) Query(letter byte) bool {
	sc.temp = sc.temp.getChild(int(letter - 'a'))
	return sc.temp.getIsEnd()
}
