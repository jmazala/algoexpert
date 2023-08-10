# https://www.algoexpert.io/questions/rectangle-mania

import collections


# TIME: O(n^2)
#   O(n) for hash
#   O(n log n) for sorting
#   O(n^2) for loops
# SPACE: O(n)
#   O(n) for hash
#   O(1) for output
def rectangleMania(coords):
    numRectangles = 0
    hash = collections.defaultdict(list)

    for [x, y] in coords:
        hash[x].append(y)

    # sort the array of coordinates by x, then y so we get every coordinate
    # as a lower left corner of a potential square
    coords.sort(key=lambda coord: (coord[0], coord[1]))

    for i, [x1, y1] in enumerate(coords):
        # [x1, y1] will be lower left

        for j in range(i + 1, len(coords)):
            [x2, y2] = coords[j]

            if x2 <= x1 or y2 <= y1:
                continue  # not LL corner

            [x3, y3] = [x1, y2]
            if y3 not in hash[x3]:
                continue  # missing a coordinate

            [x4, y4] = [x2, y1]

            if y4 not in hash[x4]:
                continue  # missing a coordinate

            numRectangles += 1

    return numRectangles


print(
    rectangleMania([[0, 0], [0, 1], [1, 1], [1, 0], [2, 1], [2, 0], [3, 1], [3, 0]])
)  # 6

print(
    rectangleMania(
        [
            [0, 0],
            [0, 1],
            [1, 1],
            [1, 0],
            [2, 1],
            [2, 0],
            [3, 1],
            [3, 0],
            [1, 3],
            [3, 3],
            [0, -4],
            [3, -4],
        ]
    )
)
