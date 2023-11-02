# 可以引⼊的库和版本相关请参考 “环境说明”
from typing import List


def isOpposite(str1: str, str2: str):
    if str1 == 'NORTH' and str2 == 'SOUTH' or str1 == 'SOUTH' and str2 == 'NORTH':
        return True
    elif str1 == 'WEST' and str2 == 'EAST' or str1 == 'EAST' and str2 == 'WEST':
        return True
    else:
        return False


def hasOpposite(arr: List[str]):
    for i in range(len(arr)-1):
        if isOpposite(arr[i], arr[i+1]):
            return i
    return -1


def dirReduc(arr: List[str]) -> List[str]:
    """
    @param arr 一维字符串数组
    @return 一维字符串数组
    """
    # 在这⾥写代码
    res = hasOpposite(arr)
    while res != -1:
        del arr[res]
        del arr[res]
        res = hasOpposite(arr)
    return arr

if __name__ == '__main__':
    arr = ["NORTH", "EAST", "WEST", "SOUTH", "WEST", "WEST"]
    res = dirReduc(arr)
    print(res)