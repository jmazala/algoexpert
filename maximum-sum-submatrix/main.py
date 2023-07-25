# https://www.algoexpert.io/questions/maximum-sum-submatrix

from typing import List

# Create a dp 3D list where dp[i][j][s] = the sum of a square s x s
# with top left corner at i,j

# To get to size s, compute squares of size s-1 and add the next row / column,
# but don't add the lower right hand corner element 2x.


# Build these square sumes all the way up to size (from function params)
# and use another var to store the max one you found

# TIME:
# O(m*n*s) to build dp array
# O(m) for outer loop
#   O(n) for inner loop
#     O(s) for innerinner loop
#       O(2s) for innermost loop
# SPACE: O(m*n*s) for dp array


def maximumSumSubmatrix(matrix: List[List[int]], size: int):
    m = len(matrix)
    n = len(matrix[0])

    dp = [[[0] * (size + 1) for _ in range(n)] for _ in range(m)]
    answer = float("-inf")

    for i in range(m):
        for j in range(n):
            for s in range(1, size + 1):
                if (i + s) > m or (j + s) > n:
                    continue

                sum = dp[i][j][s - 1]

                for t in range(0, s):
                    sum += matrix[i + t][j + s - 1]

                # loop to s-1 here to avoid double applying bottom right corner
                for t in range(0, s - 1):
                    sum += matrix[i + s - 1][j + t]

                dp[i][j][s] = sum

                if s == size:
                    answer = max(sum, answer)

    return answer


matrix = [[5, 3, -1, 5], [-7, 3, 7, 4], [12, 8, 0, 0], [1, -8, -8, 2]]
print(maximumSumSubmatrix(matrix, 1))  # 12
print(maximumSumSubmatrix(matrix, 2))  # 18
