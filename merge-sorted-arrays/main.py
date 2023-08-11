# https://www.algoexpert.io/questions/merge-sorted-arrays


import heapq
from typing import List


# METHOD 1 - Use a minHeap
#   Keep a minHeap of size k (number of arrays)
#   That heap contains 1 element from each (sorted) array as long
#   As there are remaining elements in that array
#   Top of minHeap is the next number to add to the output array
#   Keep track of current index for each array
#   NOTE:  We could also popleft from each array in arrays and avoid the indices array
# TIME: O(k) + O(nlog(k))
#   O(k) to start heap
#   O(log(k)) for heapify
#   O(k) to create indices
#   O(n) for outer loop (total # of elements in all array)
#     O(log(k)) for heappop
#     O(1) to append array
#     O(log(k)) for heappush
# SPACE: O(n + k) (but k is bounded by n...)
#   O(k) for heap
#   O(k) for indices
#   O(n) for output
def mergeSortedArrays(arrays: List[List[int]]):
    heap = [(a[0], i) for i, a in enumerate(arrays)]
    heapq.heapify(heap)
    indices = [1] * len(arrays)
    n = len(arrays)
    output = []

    while n > 0:
        (num, i) = heapq.heappop(heap)
        output.append(num)

        if indices[i] < len(arrays[i]):
            heapq.heappush(heap, (arrays[i][indices[i]], i))
            indices[i] += 1
        else:
            n -= 1

    return output


# METHOD 2 - Same as method 1, except popleft from arrays
# rather than tracking indexes.  Saves O(k) space
# At the expense of the time complexity of pop
# Which is O(l) at index 0 where l is the length of array
# being popped from.  Method 1 is more ideal.
def mergeSortedArrays(arrays: List[List[int]]):
    heap = [(a.pop(0), i) for i, a in enumerate(arrays)]
    heapq.heapify(heap)
    n = len(arrays)
    output = []

    while n > 0:
        (num, i) = heapq.heappop(heap)
        output.append(num)

        if len(arrays[i]) > 0:
            heapq.heappush(heap, (arrays[i].pop(0), i))
        else:
            n -= 1

    return output


print(
    mergeSortedArrays([[1, 5, 9, 21], [-1, 0], [-124, 81, 121], [3, 6, 12, 20, 150]])
)  # [-124, -1, 0, 1, 3, 5, 6, 9, 12, 20, 21, 81, 121, 150]

print(mergeSortedArrays([[1, 5, 9, 21], [6]]))  # [1, 5, 6, 9, 21]
