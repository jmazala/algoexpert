# https://www.algoexpert.io/questions/maximize-expression

from typing import List


# Maximize a - b + c - d
# TIME: O(n) we loop through array 4 times
# SPACE: O(n) as we store 3 arrays of length n - 1 and 1 array of length n
def maximizeExpression(array: List[int]) -> int:
    if len(array) < 4:
        return 0

    maxA = [array[0]]
    for i in range(1, len(array) - 1):
        maxA.append(max(maxA[i - 1], array[i]))

    maxAMinusB = [None, array[0] - array[1]]
    for i in range(2, len(array) - 1):
        maxAMinusB.append(max(maxAMinusB[i - 1], maxA[i - 1] - array[i]))

    maxAMinusBPlusC = [None, None, array[0] - array[1] + array[2]]
    for i in range(3, len(array) - 1):
        maxAMinusBPlusC.append(
            max(maxAMinusBPlusC[i - 1], maxAMinusB[i - 1] + array[i])
        )

    maxAMinusBPlusCMinusD = [
        None,
        None,
        None,
        array[0] - array[1] + array[2] - array[3],
    ]

    for i in range(4, len(array)):
        maxAMinusBPlusCMinusD.append(
            max(maxAMinusBPlusCMinusD[i - 1], maxAMinusBPlusC[i - 1] - array[i])
        )

    return maxAMinusBPlusCMinusD[-1]


print(maximizeExpression([3, 6, 1, -3, 2, 7]))  # 4
