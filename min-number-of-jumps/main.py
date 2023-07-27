# https://www.algoexpert.io/questions/min-number-of-jumps

from typing import List


# TIME: O(n)
# SPACE: O (1)
def minNumberOfJumps(array: List[int]) -> int:
    if len(array) == 1:
        return 0

    steps = array[0]
    maxReached = array[0]
    jumps = 0

    # we're done at the final index.  dont need to loop again
    for i in range(1, len(array) - 1):
        maxReached = max(maxReached, i + array[i])
        steps -= 1

        if steps == 0:
            jumps += 1
            steps = maxReached - i

    return jumps + 1


print(minNumberOfJumps([3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3]))  # 4
