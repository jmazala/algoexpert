# https://www.algoexpert.io/questions/monotonic-array

from typing import List


# TIME: O(n)
# SPACE: O(1)
def isMonotonic(array: List[int]) -> bool:
    if len(array) < 3:
        return True

    i = 1
    isNondecreasing = None

    while i < len(array):
        if array[i - 1] == array[i]:
            i += 1
            continue

        if isNondecreasing is None:
            isNondecreasing = array[i - 1] < array[i]

        if array[i - 1] < array[i] and not isNondecreasing:
            return False

        if array[i - 1] > array[i] and isNondecreasing:
            return False

        i += 1

    return True


print(isMonotonic([1, 2, 0]))  # False
print(isMonotonic([1, 0, 2]))  # False
print(isMonotonic([1, 1, 1]))  # True
print(isMonotonic([1, 1, 2]))  # True
print(isMonotonic([1, 1, 2, 2, 3]))  # True
print(isMonotonic([1, 1, 0, 0, -1]))  # True
print(isMonotonic([-1, -5, -10]))  # True
print(isMonotonic([1, 2, 3]))  # True
