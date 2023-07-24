# https://www.algoexpert.io/questions/interweaving-strings

from typing import List


# Use recursion + memoization
# Iterate through three and try to match a char with one or two (or both)
# TIME: O(n*m)
#   O(n1 + n2) for memo array
#   O(n*m) for string matching)
# SPACE: O(n*m) for dp array
def interweavingStrings(one: str, two: str, three: str) -> bool:
    if len(one) + len(two) != len(three):
        return False

    memo = [[None for _ in range(len(two) + 1)] for _ in range(len(one) + 1)]
    return helper(0, 0, one, two, three, memo)


def helper(
    p1: int, p2: int, one: str, two: str, three: str, memo: List[List[bool]]
) -> bool:
    if memo[p1][p2] != None:
        return memo[p1][p2]

    if (p1 + p2) == len(three):
        return True

    c3 = three[p1 + p2]

    if p1 < len(one):
        c1 = one[p1]

        if c1 == c3:
            memo[p1][p2] = helper(p1 + 1, p2, one, two, three, memo)
            if memo[p1][p2]:
                return True

    if p2 < len(two):
        c2 = two[p2]

        if c2 == c3:
            memo[p1][p2] = helper(p1, p2 + 1, one, two, three, memo)

            if memo[p1][p2]:
                return True

    memo[p1][p2] = False
    return False


print(interweavingStrings("aabcc", "dbbca", "aadbbbaccc"))  # false
print(interweavingStrings("beansprouting", "chartera", "banana"))
# false
print(interweavingStrings("abc", "def", "adbecf"))
# true
print(interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjob"))
# true
print(interweavingStrings("ababab", "ababab", "abababababab"))
# true
