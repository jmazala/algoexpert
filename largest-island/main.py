# https://www.algoexpert.io/questions/largest-island

from collections import deque
from typing import List

WATER = 1
LAND = 0
DELIMETER = ","
DIRECTIONS = [[0, 1], [0, -1], [1, 0], [-1, 0]]


# METHOD 1 -  Use BFS to find all the islands
# Iterate through water, and try to apply it to all adjacent islands to form 1 new island
# Record the size of the new island, remembering the size of the biggest one
# An island is a set of coordinates (use a string to hash them)
def largestIsland(matrix: List[List[int]]) -> int:
    [islands, water] = getIslandsAndWater(matrix)

    largestIsland = 0

    # For each water, change it to land and see how that effects an island
    for currentWater in water:
        potentialIslandSize = 1

        for currentIsland in islands:
            if isAdjacent(currentWater, currentIsland):
                potentialIslandSize += len(currentIsland)

        largestIsland = max(largestIsland, potentialIslandSize)

    return largestIsland


# BFS to find all islands
def getIslandsAndWater(matrix: List[List[int]]) -> List:
    visited = set()
    water = []
    islands = []
    m = len(matrix)
    n = len(matrix[0])

    for i in range(m):
        for j in range(n):
            if matrix[i][j] == WATER:
                water.append([i, j])
                continue

            key = getKey(i, j)
            if key in visited:
                continue

            queue = deque([[i, j]])
            island = set([key])
            visited.add(key)

            while len(queue) > 0:
                [curI, curJ] = queue.popleft()

                for [dirI, dirJ] in DIRECTIONS:
                    [nextI, nextJ] = [curI + dirI, curJ + dirJ]
                    nextKey = getKey(nextI, nextJ)

                    if (
                        nextI >= m
                        or nextJ >= n
                        or nextI < 0
                        or nextJ < 0
                        or matrix[nextI][nextJ] != LAND
                        or nextKey in visited
                    ):
                        continue

                    visited.add(nextKey)
                    island.add(nextKey)
                    queue.append([nextI, nextJ])

            islands.append(island)

    return [islands, water]


def getKey(i: int, j: int) -> str:
    return f"{i}{DELIMETER}{j}"


def isAdjacent(coordinate: List[int], island: set) -> bool:
    for [dirI, dirJ] in DIRECTIONS:
        [nextI, nextJ] = [coordinate[0] + dirI, coordinate[1] + dirJ]
        key = getKey(nextI, nextJ)
        if key in island:
            return True

    return False


# METHOD 2 - Record the size of each island, and overwrite the original array with the # of island it is
# TIME: O(m*n) as we visit every node in iteration loops
# SPACE: O(m*n) as we may be storing the entire matrix as a single island
def largestIsland(matrix: List[List[int]]) -> int:
    islandSizes = []

    # Since we're overwriting in matrix and 0 and 1 are used for WATER and LAND
    islandNumber = 2

    m = len(matrix)
    n = len(matrix[0])

    for i in range(m):
        for j in range(n):
            if matrix[i][j] == LAND:
                islandSizes.append(getIslandSize(i, j, matrix, islandNumber))
                islandNumber += 1

    largestIslandSize = 0
    for i in range(m):
        for j in range(n):
            if matrix[i][j] != WATER:
                continue

            adjacentLand = getAdjacentLand(i, j, matrix)
            adjacentIslands = set([matrix[land[0]][land[1]] for land in adjacentLand])

            islandSize = 1
            for island in adjacentIslands:
                islandSize += islandSizes[island - 2]

            largestIslandSize = max(largestIslandSize, islandSize)

    return largestIslandSize


def getIslandSize(i: int, j: int, matrix: List[int], islandNumber: int) -> int:
    m = len(matrix)
    n = len(matrix[0])
    size = 0
    queue = deque([[i, j]])

    while len(queue) > 0:
        [curI, curJ] = queue.popleft()
        if matrix[curI][curJ] != LAND:
            continue

        matrix[curI][curJ] = islandNumber
        size += 1

        for [dirI, dirJ] in DIRECTIONS:
            [nextI, nextJ] = [curI + dirI, curJ + dirJ]
            if (
                nextI >= m
                or nextJ >= n
                or nextI < 0
                or nextJ < 0
                or matrix[nextI][nextJ] != LAND
            ):
                continue

            queue.append([nextI, nextJ])

    return size


def getAdjacentLand(i: int, j: int, matrix: List[int]) -> List[List[int]]:
    m = len(matrix)
    n = len(matrix[0])

    adjacentLand = []

    for [dirI, dirJ] in DIRECTIONS:
        [nextI, nextJ] = [i + dirI, j + dirJ]

        if (
            nextI >= m
            or nextJ >= n
            or nextI < 0
            or nextJ < 0
            or matrix[nextI][nextJ] == WATER
        ):
            continue

        adjacentLand.append([nextI, nextJ])

    return adjacentLand


print(largestIsland([[0, 0, 0], [0, 1, 0], [0, 0, 0]]))  # 9
print(largestIsland([[0, 1, 1], [0, 0, 1], [1, 1, 0]]))  # 5
