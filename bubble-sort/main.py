# https://www.algoexpert.io/questions/bubble-sort

from typing import List


def bubbleSort(array: List[int]) -> List[int]:
    didSwap = True
    iterations = 0

    while didSwap:
        didSwap = False

        for i in range(len(array) - 1 - iterations):
            if array[i] > array[i + 1]:
                [array[i], array[i + 1]] = [array[i + 1], array[i]]
                didSwap = True

        iterations += 1

    return array


print(bubbleSort([8, 5, 2, 9, 5, 6, 3]))
