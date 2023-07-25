# https://www.algoexpert.io/questions/square-of-zeroes

from typing import Dict, List


def squareOfZeroes(matrix: List[int]) -> bool:
    n = len(matrix)

    if n < 2:
        return False

    zeroMap = createZeroMap(matrix)

    for i in range(len(matrix)):
        for j in range(len(matrix)):
            if matrix[i][j] == 1:
                continue

            length = 2
            while i + length <= n and j + length <= n:
                if isSquare(i, j, length, matrix, zeroMap):
                    return True

                length += 1

    return False


def isSquare(
    i: int, j: int, length: int, matrix: List[int], zeroMap: List[List[Dict[str, int]]]
) -> bool:
    top = i
    bottom = i + length - 1
    left = j
    right = j + length - 1

    return (
        zeroMap[top][left]["right"] >= length  # top row
        and zeroMap[bottom][left]["right"] >= length  # bottom row
        and zeroMap[top][left]["below"] >= length  # left col
        and zeroMap[top][right]["below"] >= length  # right col
    )


def createZeroMap(matrix) -> List[List[Dict[str, int]]]:
    zeroMap = []

    for i in reversed(range(len(matrix))):
        row = []

        for j in reversed(range(len(matrix))):
            item = {"below": 0, "right": 0}
            row.insert(0, item)

            if matrix[i][j] == 1:
                continue

            item["below"] += 1
            item["right"] += 1

            if i < len(matrix) - 1:
                item["below"] += zeroMap[0][j]["below"]

            if j < len(matrix) - 1:
                item["right"] += row[1]["right"]

        zeroMap.insert(0, row)

    return zeroMap


print(squareOfZeroes([[0, 0], [0, 0]]))  # True

print(
    squareOfZeroes(
        [
            [1, 1, 1, 0, 1, 0],
            [0, 0, 0, 0, 0, 1],
            [0, 1, 1, 1, 0, 1],
            [0, 0, 0, 1, 0, 1],
            [0, 1, 1, 1, 0, 1],
            [0, 0, 0, 0, 0, 1],
        ]
    )
)  # True

print(squareOfZeroes([[1, 1, 0, 1], [1, 0, 0, 1], [0, 0, 0, 1], [1, 1, 1, 1]]))  # True

print(squareOfZeroes([[0, 0, 0, 1], [0, 1, 1, 0], [0, 1, 0, 0], [0, 1, 0, 1]]))  # False
