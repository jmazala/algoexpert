# https://www.algoexpert.io/questions/radix-sort

from typing import List


def radixSort(array: List[int]) -> List[int]:
    if len(array) < 2:
        return array

    maxNumber = max(array)

    # counting sort on each digit place
    digit = 0

    while maxNumber / pow(10, digit) > 0:
        array = countingSort(array, digit)
        digit += 1

    return array


def getDigitFromNumber(number: int, digit: int) -> int:
    digitCol = int(pow(10, digit))
    return int(number // digitCol % 10)


def countingSort(array: List[int], digit: int) -> List[int]:
    sorted = [None for _ in array]
    counts = [0 for _ in range(10)]

    for number in array:
        digitValue = getDigitFromNumber(number, digit)
        counts[digitValue] += 1

    for i in range(1, 10):
        counts[i] += counts[i - 1]

    for number in reversed(array):
        digitValue = getDigitFromNumber(number, digit)
        counts[digitValue] -= 1
        sortedIndex = counts[digitValue]
        sorted[sortedIndex] = number

    return sorted


print(radixSort([8762, 654, 3008, 345, 87, 65, 234, 12, 2]))
