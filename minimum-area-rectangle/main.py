# https://www.algoexpert.io/questions/minimum-area-rectangle

from typing import List


def minimumAreaRectangle(points: List[int]) -> int:
    xToY = {}

    for [x, y] in points:
        if x not in xToY:
            xToY[x] = set()
        xToY[x].add(y)

    minArea = 0

    for i in range(len(points)):
        [x1, y1] = points[i]

        for j in range(i + 1, len(points)):
            [x2, y2] = points[j]

            if x2 == x1 or y2 == y1:
                continue  # not a diagonal

            [x3, y3] = [x1, y2]
            [x4, y4] = [x2, y1]

            if y3 in xToY[x3] and y4 in xToY[x4]:
                area = abs(x1 - x2) * abs(y1 - y2)
                minArea = area if minArea == 0 else min(minArea, area)

    return minArea
