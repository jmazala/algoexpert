# https://www.algoexpert.io/questions/binary-search


from typing import List


def binarySearch(array: List[int], target: int) -> int:
    if len(array) == 0:
        return -1

    low = 0
    high = len(array) - 1

    while low <= high:
        mid = (high + low) // 2

        if array[mid] == target:
            return mid

        if array[mid] > target:
            high = mid - 1
        else:
            low = mid + 1

    return -1


print(binarySearch([0, 1, 21, 33, 45, 45, 61, 71, 72, 32], 33))  # 3
