# https://www.algoexpert.io/questions/smallest-difference

from typing import List


# TIME: O(n + m) to iterate through both arrays
# SPACE: O(1)
def smallestDifference(arrayOne: List[int], arrayTwo: List[int]) -> List[int]:
    arrayOne = sorted(arrayOne)
    arrayTwo = sorted(arrayTwo)

    answer = [float("-inf"), float("inf")]

    i = 0
    j = 0

    while i < len(arrayOne) and j < len(arrayTwo):
        sum = abs(arrayOne[i] - arrayTwo[j])

        if sum < abs(answer[0] - answer[1]):
            answer = [arrayOne[i], arrayTwo[j]]

            if sum == 0:
                break

        if arrayOne[i] < arrayTwo[j]:
            i += 1
        else:
            j += 1

    return answer


print(smallestDifference([-1, 5, 10, 20, 28, 3], [26, 134, 135, 15, 17]))
