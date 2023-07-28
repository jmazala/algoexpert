# https://www.algoexpert.io/questions/longest-subarray-with-sum

from typing import List


# SLIDING WINDOW
# TIME: O(n) to loop through array
# SPACE: O(1) for answer, start, end, currentSum
def longestSubarrayWithSum(array: List[int], targetSum: int) -> List[int]:
    answer = [-1, -1]
    start = 0
    end = 0
    currentSum = 0

    # Visit every num in array
    while end < len(array):
        currentSum += array[end]

        # Shrink window if necessary
        while currentSum > targetSum and start < end:
            currentSum -= array[start]
            start += 1

        # Match case
        if currentSum == targetSum:
            if answer == [-1, -1] or answer[1] - answer[0] < end - start:
                answer = [start, end]

        end += 1

    return [] if answer == [-1, -1] else answer


print(longestSubarrayWithSum([1, 2, 3, 4, 3, 3, 1, 2, 1, 2], 10))  # [4, 8]
