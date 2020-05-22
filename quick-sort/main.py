def quickSort(array):
  helper(array, 0, len(array) - 1)
  return array

def helper(array, low, high):
  if (low >= high):
    return

  partitionIndex = partition(array, low, high)
  helper(array, low, partitionIndex - 1)
  helper(array, partitionIndex + 1, high)
  return

def partition(array, low, high):
  pivot = array[high]
  i = low
  for j in range(low, high):
    if array[j] < pivot:
      [array[i], array[j]] = [array[j], array[i]]
      i += 1

  [array[i], array[high]] = [array[high], array[i]]
  return i
