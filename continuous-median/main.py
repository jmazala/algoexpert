# https://www.algoexpert.io/questions/continuous-median

import heapq
from abc import ABC, abstractmethod


class Heap(ABC):
    @abstractmethod
    def __init__(self):
        self._data = []
        self.multiplier = None  # Abstract property for multipler

    def push(self, value: int) -> None:
        heapq.heappush(self._data, self.multiplier * value)

    def pop(self) -> int:
        return self.multiplier * heapq.heappop(self._data)

    def peek(self) -> int:
        return self.multiplier * self._data[0]

    def __len__(self) -> int:
        return len(self._data)


class MaxHeap(Heap):
    def __init__(self):
        super().__init__()
        self.multiplier = -1


class MinHeap(Heap):
    def __init__(self):
        self._data = []
        self.multiplier = 1


# Do not edit the class below except for
# the insert method. Feel free to add new
# properties and methods to the class.
class ContinuousMedianHandler:
    def __init__(self):
        # Write your code here.
        self._median = float(0)
        self._max_heap_lower_values = MaxHeap()
        self._min_heap_lower_values = MinHeap()

    def insert(self, number: int) -> None:
        # Any time you insert a number, pick the proper heap to add it to
        max_heap_length = len(self._max_heap_lower_values)
        min_heap_length = len(self._min_heap_lower_values)

        if max_heap_length == 0 and min_heap_length == 0:
            self._median = number
            self._max_heap_lower_values.push(number)
            return

        # if both heaps have equal size, determine where to insert number
        # after insertion, we'll have odd # of values, so median is middle element
        if max_heap_length == min_heap_length:
            if self._max_heap_lower_values.peek() > number:
                self._max_heap_lower_values.push(number)
                self._median = self._max_heap_lower_values.peek()
            else:
                self._min_heap_lower_values.push(number)
                self._median = self._min_heap_lower_values.peek()

            return
        # odd number of values, put newest number in appropriate heap and rebalance
        # to equal sizes.  after insertion, we have even # of values so median is avg of both

        if self._max_heap_lower_values.peek() > number:
            self._max_heap_lower_values.push(number)
            max_heap_length += 1
        else:
            self._min_heap_lower_values.push(number)
            min_heap_length += 1

        if max_heap_length > min_heap_length:
            self._min_heap_lower_values.push(self._max_heap_lower_values.pop())
        elif min_heap_length > max_heap_length:
            self._max_heap_lower_values.push(self._min_heap_lower_values.pop())

        self._median = (
            self._min_heap_lower_values.peek() + self._max_heap_lower_values.peek()
        ) / 2

    def getMedian(self) -> int:
        return self._median


c = ContinuousMedianHandler()
c.insert(5)
c.insert(10)
print(c.getMedian())  # 7.5
c.insert(100)
print(c.getMedian())  # 10
