# https://www.algoexpert.io/questions/majority-element

from typing import List


def majorityElement(array: List[int]) -> int:
    answer = array[0]
    count = 0

    for val in array:
        if count == 0:
            answer = val

        if val == answer:
            count += 1
        else:
            count -= 1

    return answer
