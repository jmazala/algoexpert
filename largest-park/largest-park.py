# https://www.algoexpert.io/questions/largest-park

from collections import deque
from typing import List


def largestPark(land: List[List[bool]]) -> int:
    m = len(land)

    if m == 0:
        return 0

    n = len(land[0])

    if n == 0:
        return 0

    heights = [0] * n
    maxArea = 0

    for row in land:
        for col in range(n):
            heights[col] = heights[col] + 1 if row[col] == False else 0

        maxArea = max(maxArea, largestRectangleHistogram(heights))

    return maxArea


def largestRectangleHistogram(heights: List[int]) -> int:
    m = len(heights)
    stack = deque()
    maxArea = 0

    for col in range(m):
        while len(stack) > 0 and heights[col] < heights[stack[-1]]:
            height = heights[stack.pop()]
            width = col if len(stack) == 0 else col - stack[-1] - 1
            maxArea = max(maxArea, width * height)

        stack.append(col)

    while len(stack) > 0:
        height = heights[stack.pop()]
        width = len(heights) if len(stack) == 0 else len(heights) - stack[-1] - 1
        maxArea = max(maxArea, width * height)

    return maxArea


print(largestPark([[True]]))  # 0
print(largestPark([[False]]))  # 1
print(largestPark([[True, False, True]]))  # 1
print(largestPark([[True, False, False]]))  # 2
print(largestPark([[True, False, False], [True, True, True]]))  # 2
print(largestPark([[True, False, False], [True, False, True]]))  # 2
print(largestPark([[True, False, False], [True, False, False]]))  # 4
