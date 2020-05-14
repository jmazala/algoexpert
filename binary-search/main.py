def binarySearch(array, target):
  if (array is None or len(array) == 0):
    return -1

  low = 0
  high = len(array) - 1

  while (low <= high):
    mid = (high + low) // 2
    
    if array[mid] == target:
      return mid
    
    if array[mid] > target:
      high = mid - 1
    else:
      low = mid + 1
    
  return -1
