def shiftedBinarySearch(array, target):
  low = 0
  high = len(array) - 1

  while (low <= high):
    if (array[low] == target):
      return low
    
    if (array[high] == target):
      return high
    
    mid = (low + high) // 2
    if (array[mid] == target):
      return mid
    
    #left half is sorted
    if (array[low] < array[mid]):
      if (target > array[low] and target < array[mid]):
        high = mid - 1
        continue
      
      low = mid + 1
      continue
    
    #right half is sorted
    if (target > array[mid] and target < array[high]):
      low = mid + 1
      continue
    
    high = mid - 1

  return -1
