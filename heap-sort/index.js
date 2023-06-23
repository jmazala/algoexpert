// https://www.algoexpert.io/questions/heap-sort

function heapSort(array) {
  if (array.length < 2) {
    return array;
  }

  buildMaxHeap(array);
  for (let high = array.length - 1; high > 0; high--) {
    swap(array, 0, high);
    siftDown(array, 0, high - 1);
  }

  return array;
}

function swap(array, i, j) {
  const temp = array[i];
  array[i] = array[j];
  array[j] = temp;
}

function buildMaxHeap(array) {
  const firstParentIndex = Math.floor((array.length - 1) / 2);
  for (let i = firstParentIndex; i >= 0; i--) {
    siftDown(array, i, array.length - 1);
  }
}

function siftDown(array, index, maxIndex) {
  while (index < maxIndex) {
    const leftChildIndex = index * 2 + 1;
    const rightChildIndex = leftChildIndex + 1;

    // no children
    if (leftChildIndex > maxIndex) {
      return;
    }

    // only a left child
    if (rightChildIndex > maxIndex) {
      if (array[index] < array[leftChildIndex]) {
        swap(array, index, leftChildIndex);
      }

      return;
    }

    // larger than both it's children.  maxHeap is built correctly
    if (array[index] > array[leftChildIndex] && array[index] > array[rightChildIndex]) {
      return;
    }

    // swap with the larger of the 2 children
    if (array[leftChildIndex] >= array[rightChildIndex]) {
      swap(array, index, leftChildIndex);
      index = leftChildIndex;
    } else {
      swap(array, index, rightChildIndex);
      index = rightChildIndex;
    }
  }
}

// Do not edit the line below.
exports.heapSort = heapSort;

console.log(heapSort([6, 5])); // [5, 6]
console.log(heapSort([8, 5, 2, 9, 5, 6, 3])); // [2, 3, 5, 5, 6, 8, 9]