# https://www.algoexpert.io/questions/zero-sum-subarray
from typing import List


def zeroSumSubarray(nums: List[int]) -> bool:
    seen = set([0])
    sum = 0

    for num in nums:
        sum += num

        if sum in seen:
            return True

        seen.add(sum)

    return False


print(zeroSumSubarray([]))  # False
print(zeroSumSubarray([1]))  # False
print(zeroSumSubarray([-5, -5, 2, 4, -2]))  # False
print(zeroSumSubarray([1, -5]))  # False
print(zeroSumSubarray([0]))  # True
print(zeroSumSubarray([1, -1]))  # True
print(zeroSumSubarray([-5, -5, 2, 3, -2]))  # True
