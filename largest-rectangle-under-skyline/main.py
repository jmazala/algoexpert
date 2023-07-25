# https://www.algoexpert.io/questions/largest-rectangle-under-skyline


from collections import deque
from typing import List


def largestRectangleUnderSkyline(buildings: List[int]) -> int:
    if len(buildings) == 0:
        return 0

    if len(buildings) == 1:
        return buildings[0]

    largest = float("-inf")
    stack = deque()

    buildings.append(0)

    for i in range(len(buildings)):
        buildingHeight = buildings[i]

        while len(stack) > 0 and stack[-1]["height"] >= buildingHeight:
            item = stack.pop()
            width = i if len(stack) == 0 else i - stack[-1]["i"] - 1
            largest = max(largest, width * item["height"])

        stack.append({"i": i, "height": buildingHeight})

    return largest


print(largestRectangleUnderSkyline([1, 3, 3, 2, 4, 1, 5, 3, 2]))  # 9
