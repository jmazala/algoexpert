def searchForRange(array, target):
    #first search for the number
	#check one index left if so binary search for left
	#check one index right if so binary search for right
	answer = [-1, -1]
	helper(array, target, 0, len(array) - 1, answer, True)
	helper(array, target, 0, len(array) - 1, answer, False)
	return answer

#recursive solution.  see index.js for iterative
def helper(array, target, low, high, answer, goLeft):
	if low > high:
		return
	
	mid = (low + high) // 2
	if array[mid] == target:
		if goLeft:
			if mid == 0 or array[mid -1] != target:
				answer[0] = mid
				return
			
			helper(array, target, low, mid - 1, answer, True)
			return
		else:
			if mid == len(array) - 1 or array[mid + 1] != target:
				answer[1] = mid
				return
			helper(array, target, mid + 1, high, answer, False)
			return
	
	if array[mid] < target:
		helper(array, target, mid + 1, high, answer, goLeft)
		return
	
	helper(array, target, low, mid - 1, answer, goLeft)