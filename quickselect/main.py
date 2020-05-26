def quickselect(array, k):
  return helper(array, k-1, 0, len(array) - 1)

#LOOP
def helper(array, position, low, high):
  while True:
    pivot = low
    left = pivot + 1
    right = high
    
    while (left <= right):
      if array[left] > array[pivot] and array[right] < array[pivot]:
        [array[left], array[right]] = [array[right], array[left]]
        left += 1
        right -= 1
        continue
      
      if array[left] <= array[pivot]:
        left += 1
      
      if array[right] >= array[pivot]:
        right -= 1
    
    [array[pivot], array[right]] = [array[right], array[pivot]]
    
    if right == position:
      return array[right]
    
    if right < position:
      low = right + 1
    else:
      high = right - 1

#RECURSIVE
# def helper(array, position, low, high):
# 	pivot = low
# 	left = pivot + 1
# 	right = high
	
# 	while (left <= right):
# 		if array[left] > array[pivot] and array[right] < array[pivot]:
# 			[array[left], array[right]] = [array[right], array[left]]
# 			left += 1
# 			right -= 1
# 			continue
		
# 		if array[left] <= array[pivot]:
# 			left += 1
		
# 		if array[right] >= array[pivot]:
# 			right -= 1
	
# 	[array[pivot], array[right]] = [array[right], array[pivot]]
	
# 	if right == position:
# 		return array[right]
	
# 	if right < position:
# 		# low = right + 1
# 		return helper(array, position, right + 1, high)
# 	else:
# 		# high = right - 1
# 		return helper(array, position, low, right - 1)