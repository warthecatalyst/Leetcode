import random
from typing import List
import scipy
import matplotlib.pyplot as plt

counters_5000 = [0 for _ in range(500)]
counters_10000 = [0 for _ in range(500)]

def one_round(datas: List[int]):
    for i in range(100):
        if datas[i] > 0:
            other = random.randint(0, 99)
            datas[i] -= 1
            datas[other] += 1

def getCounters(datas: List[int], idx):
    for data in datas:
        if idx == 4999:
            counters_5000[min(data, 499)] += 1
        else:
            counters_10000[min(data, 499)] += 1


if __name__ == '__main__':
    for loop in range(100):
        datas = [100 for _ in range(100)]
        for i in range(10000):
            one_round(datas)
            if i == 4999 or i == 9999:
                getCounters(datas, i)
    plt.plot([i for i in range(500)], counters_5000)
    plt.title("i = 5000")
    plt.show()
    plt.plot([i for i in range(500)], counters_10000)
    plt.title("i = 10000")
    plt.show()
